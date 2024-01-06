package com.solvd.bank;

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

public class JAXBMain {
    public static void main(String[] args) {

        Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());


        Country country = new Country();
        country.setId(1);
        country.setName("ExampleCountry");
        JAXBMarshaller<Country> countryJaxbMarshaller = new JAXBMarshaller<>(country);
        countryJaxbMarshaller.marshall();
        LOGGER.info("Validating Country.xml: " + (new XMLValidator<>(Country.class).validate()? "passed": "failed"));
        Country myUnmarshalledCountry = new DomParser<>(Country.class).parse();
        Country jaxbUnmarshalledCountry = countryJaxbMarshaller.unmarshall();
        LOGGER.info(myUnmarshalledCountry);
        LOGGER.info(jaxbUnmarshalledCountry);

        City city = new City();
        city.setId(1);
        city.setName("ExampleCity");
        city.setCountry(country);
        JAXBMarshaller<City> cityJAXBMarshaller = new JAXBMarshaller<>(city);
        cityJAXBMarshaller.marshall();
        LOGGER.info("Validating City.xml: " + (new XMLValidator<>(City.class).validate()? "passed": "failed"));
        City myUnmarshalledCity = new DomParser<>(City.class).parse();
        City jaxbUnmarshalledCity = cityJAXBMarshaller.unmarshall();
        LOGGER.info(myUnmarshalledCity);
        LOGGER.info(jaxbUnmarshalledCity);


        Location location = new Location();
        location.setId(1);
        location.setCity(city);
        location.setZipCode("1234");
        location.setAddress("ExampleAddress");
        JAXBMarshaller<Location> locationJAXBMarshaller = new JAXBMarshaller<>(location);
        locationJAXBMarshaller.marshall();
        LOGGER.info("Validating Location.xml: " + (new XMLValidator<>(Location.class).validate()? "passed": "failed"));
        Location myUnmarshalledLocation = new DomParser<Location>(Location.class).parse();
        Location jaxbUnmarshalledLocation = locationJAXBMarshaller.unmarshall();
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
        JAXBMarshaller<Associate> associateJAXBMarshaller = new JAXBMarshaller<>(associate);
        associateJAXBMarshaller.marshall();
        LOGGER.info("Validating associate.xml: " + (new XMLValidator<>(Associate.class).validate()? "passed": "failed"));
        Associate myUnmarshalledAssociate = new DomParser<Associate>(Associate.class).parse();
        Associate jaxbUnmarshalledAssociate = associateJAXBMarshaller.unmarshall();
        LOGGER.info(myUnmarshalledAssociate);
        LOGGER.info(jaxbUnmarshalledAssociate);


        Position position = new Position();
        position.setId(1);
        position.setSalary(70000);
        position.setPosition("ExamplePosition");
        JAXBMarshaller<Position> positionJAXBMarshaller = new JAXBMarshaller<>(position);
        positionJAXBMarshaller.marshall();
        LOGGER.info("Validating position.xml: " + (new XMLValidator<>(Position.class).validate()? "passed": "failed"));
        Position myUnmarshalledPosition = new DomParser<>(Position.class).parse();
        Position jaxbUnmarshalledPosition = positionJAXBMarshaller.unmarshall();
        LOGGER.info(myUnmarshalledPosition);
        LOGGER.info(jaxbUnmarshalledPosition);

        Staff staff = new Staff();
        staff.setPosition(position);
        staff.setDateHired(new Timestamp(System.currentTimeMillis()));
        staff.setAssociate(associate);
        JAXBMarshaller<Staff> staffJAXBMarshaller = new JAXBMarshaller<>(staff);
        staffJAXBMarshaller.marshall();
        LOGGER.info(staffJAXBMarshaller.unmarshall());

        Branch branch = new Branch();
        branch.setId(1);
        branch.setBranchName("ExampleBranch");
        branch.setBranchStaff(new ArrayList<Staff>());
        branch.getBranchStaff().add(staff);
        JAXBMarshaller<Branch> branchJAXBMarshaller = new JAXBMarshaller<>(branch);
        branchJAXBMarshaller.marshall();
        LOGGER.info(branchJAXBMarshaller.unmarshall());

        BranchHasEmployee branchHasEmployee = new BranchHasEmployee();
        branchHasEmployee.setBranch(branch);
        branchHasEmployee.setStaff(staff);
        JAXBMarshaller<BranchHasEmployee> branchHasEmployeeJAXBMarshaller = new JAXBMarshaller<>(branchHasEmployee);
        branchHasEmployeeJAXBMarshaller.marshall();
        LOGGER.info(branchHasEmployeeJAXBMarshaller.unmarshall());

        Merchant merchant = new Merchant();
        merchant.setAssociate(associate);
        JAXBMarshaller<Merchant> merchantJAXBMarshaller = new JAXBMarshaller<>(merchant);
        merchantJAXBMarshaller.marshall();
        LOGGER.info(merchantJAXBMarshaller.unmarshall());

        Customer customer = new Customer();
        customer.setCreditScore(9000.00);
        customer.setAssociate(associate);
        JAXBMarshaller<Customer> customerJAXBMarshaller = new JAXBMarshaller<>(customer);
        customerJAXBMarshaller.marshall();
        LOGGER.info(customerJAXBMarshaller.unmarshall());

        Account account = new Account();
        account.setAmount(800.30);
        account.setBranch(branch);
        account.setId(1);
        account.setHolds(false);
        account.setCustomer(customer);
        account.setDateCreated(new Timestamp(System.currentTimeMillis()));
        JAXBMarshaller<Account> accountJAXBMarshaller = new JAXBMarshaller<>(account);
        accountJAXBMarshaller.marshall();
        LOGGER.info(accountJAXBMarshaller.unmarshall());

        TransferStatus transferStatus = new TransferStatus();
        transferStatus.setStatus(StatusNames.ACCEPTED.getSTATUS());
        transferStatus.setId(1);
        JAXBMarshaller<TransferStatus> transferStatusJAXBMarshaller = new JAXBMarshaller<>(transferStatus);
        transferStatusJAXBMarshaller.marshall();
        LOGGER.info(transferStatusJAXBMarshaller.unmarshall());

        Transfer transfer = new Transfer();
        transfer.setTransferTime(new Timestamp(System.currentTimeMillis()));
        transfer.setId(1);
        transfer.setSender(account);
        transfer.setReceiver(account);
        transfer.setAmount(400);
        transfer.setTransferStatus(transferStatus);
        JAXBMarshaller<Transfer> transferJAXBMarshaller = new JAXBMarshaller<>(transfer);
        LOGGER.info(transferJAXBMarshaller.unmarshall());

        Card card = new Card();
        card.setCardNumber(":1232123");
        card.setAccount(account);
        card.setCvv("123");
        card.setId(1);
        card.setExpirationDate(new Timestamp(System.currentTimeMillis()));
        JAXBMarshaller<Card> cardJAXBMarshaller = new JAXBMarshaller<>(card);
        cardJAXBMarshaller.marshall();
        LOGGER.info(cardJAXBMarshaller.unmarshall());

        Transaction transaction = new Transaction();
        transaction.setCard(card);
        transaction.setId(1);
        transaction.setTransactionTime(new Timestamp(System.currentTimeMillis()));
        transaction.setMerchant(merchant);
        transaction.setTransferStatus(transferStatus);
        transaction.setAmount(123.21);
        JAXBMarshaller<Transaction> transactionJAXBMarshaller = new JAXBMarshaller<>(transaction);
        transactionJAXBMarshaller.marshall();
        LOGGER.info(transactionJAXBMarshaller.unmarshall());
    }
}
