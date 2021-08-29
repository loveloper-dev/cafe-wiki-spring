package com.duzon.lulu.allergy.mapper;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface AllergyMapper {

    List<HashMap> getAllergyList();

    List<HashMap> getAllergiesFromMenu();

    int getAllergyIdxMap(HashMap param);

    int updateAllergyStringToCode(HashMap param);

}
