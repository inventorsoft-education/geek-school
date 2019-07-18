package com.geekschool.validation;

import com.geekschool.dto.lections.LectionDateDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LectionDatesNotOverlapValidator
        implements ConstraintValidator<LectionDatesNotOverlap, List<LectionDateDto>> {

    @Override
    public void initialize(LectionDatesNotOverlap constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<LectionDateDto> value, ConstraintValidatorContext context) {
        Set<LocalDateTime> startUnique = new HashSet<>();
        Set<LocalDateTime> endUnique = new HashSet<>();

        Set<LectionDateDto> duplicates = value.stream()
                .filter(s -> ! startUnique.add(s.getStartDate())
                          || ! endUnique.add(s.getEndDate()))
                .collect(Collectors.toSet());

        return duplicates.isEmpty();
    }
}
