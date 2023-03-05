package com.citizen.camunda.poc.generator;

import com.citizen.camunda.poc.model.ReferenceTypeModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CsvFileGenerator {

    public void writeStudentsToCsv(List<ReferenceTypeModel> referenceTypeModels, Writer writer) {
        try {
            writer.append("ID,Reference Type,Reference Type Desc,Reference Value,Reference Value Desc,Effective StartDate,Effective EndDate");
            writer.append("\n");
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);

            for (ReferenceTypeModel referenceTypeModel : referenceTypeModels) {
                printer.printRecord(referenceTypeModel.getId(), referenceTypeModel.getReferenceType(), referenceTypeModel.getReferenceTypeDesc(),
                        referenceTypeModel.getReferenceValue(), referenceTypeModel.getReferenceValueDesc(),
                        referenceTypeModel.getEffectiveStartDate(), referenceTypeModel.getEffectiveEndDate());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
