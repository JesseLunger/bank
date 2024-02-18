package com.solvd.bank.utils.tests;

import com.solvd.bank.domain.*;
import com.solvd.bank.utils.enums.StatusNames;
import com.solvd.bank.utils.xmlutils.DomParser;
import com.solvd.bank.utils.xmlutils.JAXBUtil;
import com.solvd.bank.utils.xmlutils.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Timestamp;
import java.util.ArrayList;

public class JAXBTest {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void test() {

        String filePath = System.getProperty("user.dir") + "/src/main/resources/jsonclasses/";

        Country country = new Country();
        country.setId(1);
        country.setName("ExampleCountry");
        JAXBUtil.marshall(filePath, country);
        LOGGER.info("Validating Country.xml: " + (new XMLValidator<>(Country.class).validate() ? "passed" : "failed"));
        Country myUnmarshalledCountry = new DomParser<>(Country.class).parse();
        Country jaxbUnmarshalledCountry = JAXBUtil.unmarshall(filePath, country);
        LOGGER.info(myUnmarshalledCountry);
        LOGGER.info(jaxbUnmarshalledCountry);

        City city = new City();
        city.setId(1);
        city.setName("ExampleCity");
        city.setCountry(country);
        JAXBUtil.marshall(filePath, city);
        LOGGER.info("Validating City.xml: " + (new XMLValidator<>(City.class).validate() ? "passed" : "failed"));
        City myUnmarshalledCity = new DomParser<>(City.class).parse();
        City jaxbUnmarshalledCity = JAXBUtil.unmarshall(filePath, city);
        LOGGER.info(myUnmarshalledCity);
        LOGGER.info(jaxbUnmarshalledCity);

        Location location = new Location();
        location.setId(1);
        location.setCity(city);
        location.setZipCode("1234");
        location.setAddress("ExampleAddress");
        JAXBUtil.marshall(filePath, location);
        LOGGER.info("Validating Location.xml: " + (new XMLValidator<>(Location.class).validate() ? "passed" : "failed"));
        Location myUnmarshalledLocation = new DomParser<Location>(Location.class).parse();
        Location jaxbUnmarshalledLocation = JAXBUtil.unmarshall(filePath, location);
        LOGGER.info(myUnmarshalledLocation);
        LOGGER.info(jaxbUnmarshalledLocation);

        Associate associate = new Associate();
        associate.setId(1);
        associate.setLocation(location);
        associate.setPrimaryName("Example");
        associate.setSecondaryName("Associate");
        associate.setEmail("example@examplemail.com");
        associate.setDateJoined(new Timestamp(System.currentTimeMillis()));
        associate.setPhoneNumber("543-342-2342");

        JAXBUtil.marshall(filePath, associate);
        LOGGER.info("Validating associate.xml: " + (new XMLValidator<>(Associate.class).validate() ? "passed" : "failed"));
        Associate myUnmarshalledAssociate = new DomParser<Associate>(Associate.class).parse();
        Associate jaxbUnmarshalledAssociate = JAXBUtil.unmarshall(filePath, associate);
        LOGGER.info(myUnmarshalledAssociate);
        LOGGER.info(jaxbUnmarshalledAssociate);

        Position position = new Position();
        position.setId(1);
        position.setSalary(70000);
        position.setPosition("ExamplePosition");
        JAXBUtil.marshall(filePath, position);
        LOGGER.info("Validating position.xml: " + (new XMLValidator<>(Position.class).validate() ? "passed" : "failed"));
        Position myUnmarshalledPosition = new DomParser<>(Position.class).parse();
        Position jaxbUnmarshalledPosition = JAXBUtil.unmarshall(filePath, position);
        LOGGER.info(myUnmarshalledPosition);
        LOGGER.info(jaxbUnmarshalledPosition);

        Staff staff = new Staff();
        staff.setPosition(position);
        staff.setDateHired(new Timestamp(System.currentTimeMillis()));
        staff.setAssociate(associate);
        JAXBUtil.marshall(filePath, staff);
        LOGGER.info(JAXBUtil.unmarshall(filePath, staff));

        Branch branch = new Branch();
        branch.setId(1);
        branch.setBranchName("ExampleBranch");
        branch.setBranchStaff(new ArrayList<Staff>());
        branch.getBranchStaff().add(staff);
        JAXBUtil.marshall(filePath, branch);
        LOGGER.info(JAXBUtil.unmarshall(filePath, branch));

        BranchHasEmployee branchHasEmployee = new BranchHasEmployee();
        branchHasEmployee.setBranch(branch);
        branchHasEmployee.setStaff(staff);
        JAXBUtil.marshall(filePath, branchHasEmployee);
        LOGGER.info(JAXBUtil.unmarshall(filePath, branchHasEmployee));

        Merchant merchant = new Merchant();
        merchant.setAssociate(associate);
        JAXBUtil.marshall(filePath, merchant);
        LOGGER.info(JAXBUtil.unmarshall(filePath, merchant));

        Customer customer = new Customer();
        customer.setCreditScore(9000.00);
        customer.setAssociate(associate);
        JAXBUtil.marshall(filePath, customer);
        LOGGER.info(JAXBUtil.unmarshall(filePath, customer));

        Account account = new Account();
        account.setAmount(800.30);
        account.setBranch(branch);
        account.setId(1);
        account.setHolds(false);
        account.setCustomer(customer);
        account.setDateCreated(new Timestamp(System.currentTimeMillis()));
        JAXBUtil.marshall(filePath, account);
        LOGGER.info(JAXBUtil.unmarshall(filePath, account));

        TransferStatus transferStatus = new TransferStatus();
        transferStatus.setStatus(StatusNames.ACCEPTED.getStatus());
        transferStatus.setId(1);
        JAXBUtil.marshall(filePath, transferStatus);
        LOGGER.info(JAXBUtil.unmarshall(filePath, transferStatus));

        Transfer transfer = new Transfer();
        transfer.setTransferTime(new Timestamp(System.currentTimeMillis()));
        transfer.setId(1);
        transfer.setSender(account);
        transfer.setReceiver(account);
        transfer.setAmount(400);
        transfer.setTransferStatus(transferStatus);
        JAXBUtil.marshall(filePath, transfer);
        LOGGER.info(JAXBUtil.unmarshall(filePath, transfer));

        Card card = new Card();
        card.setCardNumber(":1232123");
        card.setAccount(account);
        card.setCvv("123");
        card.setId(1);
        card.setExpirationDate(new Timestamp(System.currentTimeMillis()));
        JAXBUtil.marshall(filePath, card);
        LOGGER.info(JAXBUtil.unmarshall(filePath, card));

        Transaction transaction = new Transaction();
        transaction.setCard(card);
        transaction.setId(1);
        transaction.setTransactionTime(new Timestamp(System.currentTimeMillis()));
        transaction.setMerchant(merchant);
        transaction.setTransferStatus(transferStatus);
        transaction.setAmount(123.21);
        JAXBUtil.marshall(filePath, transaction);
        LOGGER.info(JAXBUtil.unmarshall(filePath, transaction));
    }
}
