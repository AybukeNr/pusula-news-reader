package com.example.pusula.Repository;

import com.example.pusula.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleDAO  {
    Role findById(int id);

}
