/*
 * Copyright (c) 2021 Intracom Telecom. All rights reserved.
 */
package com.example;

import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ValueMapping;

@Mapper
public interface TestMapper {
    @Named("unwrap")
    default  <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }

    class Customer {
        private enum Status { ENABLED, DISABLED }

        private Status status;

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status stat) {
            this.status = stat;
        }
    }

    class CustomerDTO {
        private enum Status { ENABLED, DISABLED }

        private Status status;

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        public void setStatus(Status stat) {
            this.status = stat;
        }
    }

    @Mapping(target = "status", source = "status", qualifiedByName = "unwrap")
    Customer map(CustomerDTO value);

    @ValueMapping(source = "ENABLED", target = "ENABLED")
    @ValueMapping(source = "DISABLED", target = "DISABLED")
    Customer.Status map(CustomerDTO.Status status);
}