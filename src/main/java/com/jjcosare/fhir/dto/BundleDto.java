package com.jjcosare.fhir.dto;

import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.r4.model.Bundle;

import java.util.List;

/**
 * Created by jjcosare on 3/7/19.
 */
public class BundleDto {

    public String selfLinkUrl;

    public String nextLinkUrl;

    public String previousLinkUrl;

    public List<?> resourceList;

    public BundleDto() {}

    public BundleDto(Bundle bundle, List<?> resourceList) {
        for(Bundle.BundleLinkComponent bundleLinkComponent : bundle.getLink()){
            switch (bundleLinkComponent.getRelation()){
                case IBaseBundle.LINK_SELF :
                    this.selfLinkUrl = bundleLinkComponent.getUrl();
                    break;
                case IBaseBundle.LINK_NEXT :
                    this.nextLinkUrl = bundleLinkComponent.getUrl();
                    break;
                case IBaseBundle.LINK_PREV :
                    this.previousLinkUrl = bundleLinkComponent.getUrl();
                    break;
            }
        }
        this.resourceList = resourceList;
    }

    public String getSelfLinkUrl() {
        return selfLinkUrl;
    }

    public void setSelfLinkUrl(String selfLinkUrl) {
        this.selfLinkUrl = selfLinkUrl;
    }

    public String getNextLinkUrl() {
        return nextLinkUrl;
    }

    public void setNextLinkUrl(String nextLinkUrl) {
        this.nextLinkUrl = nextLinkUrl;
    }

    public String getPreviousLinkUrl() {
        return previousLinkUrl;
    }

    public void setPreviousLinkUrl(String previousLinkUrl) {
        this.previousLinkUrl = previousLinkUrl;
    }

    public List<?> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<?> resourceList) {
        this.resourceList = resourceList;
    }
}
