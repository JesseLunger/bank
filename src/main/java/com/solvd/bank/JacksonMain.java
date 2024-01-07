package com.solvd.bank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.bank.domain.*;
import com.solvd.bank.utils.enums.StatusNames;
import com.solvd.bank.utils.xmlutils.DomParser;
import com.solvd.bank.utils.xmlutils.JAXBMarshaller;
import com.solvd.bank.utils.xmlutils.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Timestamp;
import java.util.ArrayList;

public class JacksonMain {
    public static void main(String[] args){

        Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

        Country country = new Country();
        country.setId(1);
        country.setName("ExampleCountry");
        LOGGER.info("\n" + Country.class.getSimpleName() + "\n" + convertToJson(country));

        City city = new City();
        city.setId(1);
        city.setName("ExampleCity");
        city.setCountry(country);
        LOGGER.info("\n" + City.class.getSimpleName() + "\n" + convertToJson(city));

        Location location = new Location();
        location.setId(1);
        location.setCity(city);
        location.setZipCode("1234");
        location.setAddress("ExampleAddress");
        LOGGER.info("\n" + Location.class.getSimpleName() + "\n" + convertToJson(location));

        Associate associate = new Associate();
        associate.setId(1);
        associate.setLocation(location);
        associate.setPrimaryName("Example");
        associate.setSecondaryName("Associate");
        associate.setEmail("example@examplemail.com");
        associate.setDateJoined(new Timestamp(System.currentTimeMillis()));
        associate.setPhoneNumber("543-342-2342");
        LOGGER.info("\n" + Associate.class.getSimpleName() + "\n" + convertToJson(associate));

        Position position = new Position();
        position.setId(1);
        position.setSalary(70000);
        position.setPosition("ExamplePosition");
        LOGGER.info("\n" + Position.class.getSimpleName() + "\n" + convertToJson(position));

        Staff staff = new Staff();
        staff.setPosition(position);
        staff.setDateHired(new Timestamp(System.currentTimeMillis()));
        staff.setAssociate(associate);
        LOGGER.info("\n" + Staff.class.getSimpleName() + "\n" + convertToJson(staff));

        Branch branch = new Branch();
        branch.setId(1);
        branch.setBranchName("ExampleBranch");
        branch.setBranchStaff(new ArrayList<Staff>());
        branch.getBranchStaff().add(staff);
        LOGGER.info("\n" + Branch.class.getSimpleName() + "\n" + convertToJson(branch));

        BranchHasEmployee branchHasEmployee = new BranchHasEmployee();
        branchHasEmployee.setBranch(branch);
        branchHasEmployee.setStaff(staff);
        LOGGER.info("\n" + BranchHasEmployee.class.getSimpleName() + "\n" + convertToJson(branchHasEmployee));

        Merchant merchant = new Merchant();
        merchant.setAssociate(associate);
        LOGGER.info("\n" + Merchant.class.getSimpleName() + "\n" + convertToJson(merchant));

        Customer customer = new Customer();
        customer.setCreditScore(9000.00);
        customer.setAssociate(associate);
        LOGGER.info("\n" + Customer.class.getSimpleName() + "\n" + convertToJson(customer));

        Account account = new Account();
        account.setAmount(800.30);
        account.setBranch(branch);
        account.setId(1);
        account.setHolds(false);
        account.setCustomer(customer);
        account.setDateCreated(new Timestamp(System.currentTimeMillis()));
        LOGGER.info("\n" + Account.class.getSimpleName() + "\n" + convertToJson(account));

        TransferStatus transferStatus = new TransferStatus();
        transferStatus.setStatus(StatusNames.ACCEPTED.getSTATUS());
        transferStatus.setId(1);
        LOGGER.info("\n" + TransferStatus.class.getSimpleName() + "\n" + convertToJson(transferStatus));

        Transfer transfer = new Transfer();
        transfer.setTransferTime(new Timestamp(System.currentTimeMillis()));
        transfer.setId(1);
        transfer.setSender(account);
        transfer.setReceiver(account);
        transfer.setAmount(400);
        transfer.setTransferStatus(transferStatus);
        LOGGER.info("\n" + Transfer.class.getSimpleName() + "\n" + convertToJson(transfer));

        Card card = new Card();
        card.setCardNumber(":1232123");
        card.setAccount(account);
        card.setCvv("123");
        card.setId(1);
        card.setExpirationDate(new Timestamp(System.currentTimeMillis()));
        LOGGER.info("\n" + Card.class.getSimpleName() + "\n" + convertToJson(card));

        Transaction transaction = new Transaction();
        transaction.setCard(card);
        transaction.setId(1);
        transaction.setTransactionTime(new Timestamp(System.currentTimeMillis()));
        transaction.setMerchant(merchant);
        transaction.setTransferStatus(transferStatus);
        transaction.setAmount(123.21);
        LOGGER.info( "\n" + Transaction.class.getSimpleName() + "\n" + convertToJson(transaction));



    }

    private static String convertToJson(Object targetClass){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try{
            return objectMapper.writeValueAsString(targetClass);
        } catch (JsonProcessingException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
