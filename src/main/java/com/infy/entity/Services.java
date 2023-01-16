package com.infy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Services {
    @Id
    private Integer serviceId;
    private String serviceName;
    private Integer serviceCost;

    public Integer getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServiceCost() {
        return this.serviceCost;
    }

    public void setServiceCost(Integer serviceCost) {
        this.serviceCost = serviceCost;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", serviceCost=" + serviceCost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (null == o || this.getClass() != o.getClass()) return false;
        Services service = (Services) o;
        return this.serviceId.equals(service.serviceId) && this.serviceName.equals(service.serviceName) && this.serviceCost.equals(service.serviceCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.serviceId, this.serviceName, this.serviceCost);
    }
}
