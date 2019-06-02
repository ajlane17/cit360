package com.company;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "department", schema = "rnd", catalog = "")
public class DepartmentEntity {
    private int id;
    private String departmentName;

    public DepartmentEntity() {
    }

    public DepartmentEntity(int id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "department_name")
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return id == that.id &&
                departmentName.equals(that.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departmentName);
    }
}
