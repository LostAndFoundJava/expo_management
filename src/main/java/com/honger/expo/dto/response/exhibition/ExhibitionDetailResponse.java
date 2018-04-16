package com.honger.expo.dto.response.exhibition;

import com.honger.expo.dto.vo.ExhibitionSearchVO;
import com.honger.expo.pojo.Exhibition;
import com.honger.expo.pojo.ExhibitionDetail;
import com.honger.expo.pojo.FileResource;

import java.util.List;

/**
 * Created by chenjian on 2018/4/16.
 */
public class ExhibitionDetailResponse {
    private com.honger.expo.pojo.ExhibitionDetail exhibitionDetail;
    private List<FileResource> fileResource;
    private ExhibitionSearchVO exhibition;

    public com.honger.expo.pojo.ExhibitionDetail getExhibitionDetail() {
        return exhibitionDetail;
    }

    public void setExhibitionDetail(com.honger.expo.pojo.ExhibitionDetail exhibitionDetail) {
        this.exhibitionDetail = exhibitionDetail;
    }

    public List<FileResource> getFileResource() {
        return fileResource;
    }

    public void setFileResource(List<FileResource> fileResource) {
        this.fileResource = fileResource;
    }

    public ExhibitionSearchVO getExhibition() {
        return exhibition;
    }

    public void setExhibition(ExhibitionSearchVO exhibition) {
        this.exhibition = exhibition;
    }
}
