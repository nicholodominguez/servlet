package com.ecc.ems;

import java.util.Set;
import com.ecc.ems.Employee;

import javax.persistence.Entity;
import javax.persistence.Cacheable;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.AttributeOverride;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "Role")
@Table (name = "role")
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
public class Role extends BaseEntity{
    protected String name;
    protected Set<Employee> employees;
    
    public Role() {}
    
    public Role(String name, boolean status) {
        super(status);
        this.name = name;    
    }
    
    @Column (name = "role_name")
    public String getName() {
        return name;
    }
    
    @ManyToMany(
        targetEntity = com.ecc.ems.Employee.class,
        fetch = FetchType.EAGER
    )
    @Cascade( CascadeType.ALL )
	@JoinTable(
        name = "emp_role",
	    joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) },
        inverseJoinColumns = { @JoinColumn(name = "emp_id", nullable = false, updatable = false) }
    )
    public Set<Employee> getEmployees() {
        return employees;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
