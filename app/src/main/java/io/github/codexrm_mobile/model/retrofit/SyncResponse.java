package io.github.codexrm_mobile.model.retrofit;

import java.util.List;

import io.github.codexrm_mobile.model.dto.PageDTO;
import io.github.codexrm_mobile.model.dto.ReferenceDTO;

public class SyncResponse {

    private List<ReferenceDTO> referenceDTOList;
    private PageDTO pageDTO;

    public SyncResponse() { }

    public SyncResponse(List<ReferenceDTO> referenceDTOList, PageDTO pageDTO) {
        this.referenceDTOList = referenceDTOList;
        this.pageDTO = pageDTO;
    }

    public List<ReferenceDTO> getReferenceDTOList() {
        return referenceDTOList;
    }

    public void setReferenceDTOList(List<ReferenceDTO> referenceDTOList) { this.referenceDTOList = referenceDTOList; }

    public PageDTO getPageDTO() {
        return pageDTO;
    }

    public void setPageDTO(PageDTO pageDTO) {
        this.pageDTO = pageDTO;
    }
}
