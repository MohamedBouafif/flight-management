
package com.mycompany.flights;

import java.util.Objects;

public record Airport(String name,String address,String code) {
    public Airport{
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException("Airport name cannot be empty");
        }
        if(address==null || address.isEmpty()){
            throw new IllegalArgumentException("Airport name cannot be empty");
        }
        if(code==null || code.length() < 3){
            throw new IllegalArgumentException("Airport code must be at least 3 letters long");
        }
    }
    
    @Override
    public String toString() {
        return "Airport{" + "nom=" + name + ", address=" + address + ", code=" + code + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Airport other = (Airport) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return Objects.equals(this.code, other.code);
    }
    
}

