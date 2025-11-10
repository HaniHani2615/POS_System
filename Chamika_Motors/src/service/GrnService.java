package service;

import dto.GrnDto;
import dto.GrnItemDto;
import java.util.List;

/**
 * Service interface for GRN (Goods Received Note) operations.
 */
public interface GrnService {
    /**
     * Saves a GRN with its items.
     * @param grnDto the GRN data
     * @throws Exception if operation fails
     */
    void saveGrn(GrnDto grnDto) throws Exception;

    /**
     * Processes GRN items and updates stock accordingly.
     * @param grnId the GRN ID
     * @param items list of GRN items
     * @throws Exception if operation fails
     */
    void processGrnItems(String grnId, List<GrnItemDto> items) throws Exception;
}
