package au.com.ms.validator;


import au.com.ms.util.AppUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.regex.Pattern;

public class EmailListValidator implements ConstraintValidator<EmailList,List<String>> {
    final private Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
    @Override
    public void initialize(final EmailList constraintAnnotation) {
        //for interface
    }
    @Override
    public boolean isValid(final List<String> value, final ConstraintValidatorContext context) {
        return !(value == null || value.isEmpty()) && value.stream().filter(e -> !AppUtils.isNullOrEmpty(e)).filter(e -> emailPattern.matcher(e).matches()).count() == value.size();
    }
}
