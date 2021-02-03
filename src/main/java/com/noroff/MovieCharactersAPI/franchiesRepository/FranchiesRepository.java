package com.noroff.MovieCharactersAPI.franchiesRepository;

import com.noroff.MovieCharactersAPI.models.Franchie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface FranchiesRepository extends JpaRepository<Franchie,Long> {


}
