package br.com.fiap.aluno.util;

import br.com.fiap.aluno.dto.TransactionDTO;
import br.com.fiap.aluno.entity.Transaction;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.List;


/**
 * Classe que contem os metodos para criar o arquivo csv
 *
 * @author Alan Ricarte Salomão
 */
public class WriteCSVToResponse {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriteCSVToResponse.class);

    public static void writeTransactions(PrintWriter printWriter, List<TransactionDTO> transactionDTOList){

        try{
            ColumnPositionMappingStrategy<TransactionDTO> mappingStrategy = new ColumnPositionMappingStrategy<>();

            mappingStrategy.setType(TransactionDTO.class);

            String[] columns = new String[]{"id", "data", "valor", "aluno.id", "aluno.nome", "aluno.cpf", "aluno.rm"};
            mappingStrategy.setColumnMapping(columns);

            StatefulBeanToCsv<TransactionDTO> btcsv = new StatefulBeanToCsvBuilder<TransactionDTO>(printWriter)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mappingStrategy)
                    .withSeparator(',')
                    .build();

            btcsv.write(transactionDTOList);
        }catch(CsvException ex){
            LOGGER.error("Error mapping Bean to CSV", ex);
        }
    }

}
