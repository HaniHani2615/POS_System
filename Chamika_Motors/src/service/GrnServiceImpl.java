package service;

import dto.GrnDto;
import dto.GrnItemDto;
import repository.GrnRepository;
import repository.GrnItemRepository;
import repository.StockRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of GrnService.
 */
public class GrnServiceImpl implements GrnService {

    private static final Logger logger = Logger.getLogger(GrnServiceImpl.class.getName());
    private final GrnRepository grnRepository;
    private final GrnItemRepository grnItemRepository;
    private final StockRepository stockRepository;

    public GrnServiceImpl(GrnRepository grnRepository, GrnItemRepository grnItemRepository, 
                         StockRepository stockRepository) {
        this.grnRepository = grnRepository;
        this.grnItemRepository = grnItemRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public void saveGrn(GrnDto grnDto) throws Exception {
        try {
            // Save GRN header
            grnRepository.createGrn(
                grnDto.getId(),
                grnDto.getSupplierId(),
                grnDto.getEmployeeMobile(),
                grnDto.getDateTime(),
                grnDto.getPaidAmount()
            );

            // Process GRN items
            processGrnItems(grnDto.getId(), grnDto.getItems());

            logger.info("GRN saved successfully: " + grnDto.getId());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving GRN: " + grnDto.getId(), e);
            throw new Exception("Failed to save GRN", e);
        }
    }

    @Override
    public void processGrnItems(String grnId, List<GrnItemDto> items) throws Exception {
        for (GrnItemDto item : items) {
            String stockId = processStockForGrnItem(item);
            
            // Create GRN item entry
            grnItemRepository.createGrnItem(
                stockId,
                item.getQty(),
                item.getBuyingPrice(),
                grnId
            );
        }
    }

    /**
     * Processes stock for a GRN item. Either updates existing stock or creates new.
     * @param item the GRN item
     * @return the stock ID
     * @throws Exception if operation fails
     */
    private String processStockForGrnItem(GrnItemDto item) throws Exception {
        Optional<String> existingStockId = stockRepository.findStockId(
            item.getProductId(),
            item.getSellingPrice(),
            item.getMfg(),
            item.getExp()
        );

        if (existingStockId.isPresent()) {
            // Stock exists, update quantity
            String stockId = existingStockId.get();
            double currentQty = stockRepository.getStockQuantity(stockId);
            double newQty = currentQty + item.getQty();
            stockRepository.updateStockQuantity(stockId, newQty);
            return stockId;
        } else {
            // Stock doesn't exist, create new
            return stockRepository.createStock(
                item.getProductId(),
                item.getQty(),
                item.getSellingPrice(),
                item.getMfg(),
                item.getExp()
            );
        }
    }
}
