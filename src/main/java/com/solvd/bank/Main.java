package com.solvd.bank;

import com.solvd.bank.domain.*;
import com.solvd.bank.persistence.impl.*;
import com.solvd.bank.utils.ConnectionPool;
import com.solvd.bank.utils.DBConfig;
import org.apache.ibatis.jdbc.SQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {



        LOGGER.info("-----Testing CountryDAO-----");
        CountryDAO countriesJdbc = new CountryDAO();
        Country testCountry = new Country();
        testCountry.setName("testCountry");
        countriesJdbc.saveEntity(testCountry);
        testCountry.setName("nameChange");
        countriesJdbc.updateEntity(testCountry);
        testCountry = countriesJdbc.getEntityById(testCountry.getId());
        LOGGER.info("Testing CountryDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testCountry.getName().equals("nameChange")) ? "passed" : "failed"));
        countriesJdbc.removeEntityById(testCountry.getId());
        testCountry = countriesJdbc.getEntityById(testCountry.getId());
        LOGGER.info("Testing CountryDAO.deleteById: "
                + ((testCountry == null) ? "passed" : "failed"));
        Country getCountry = countriesJdbc.getAll().get(0);
        LOGGER.info("Testing CountryDAO.getAll: "
                + ((getCountry != null) ? "passed" : "failed"));
        LOGGER.info("Testing CountryDAO.getAllLocationsByCountry: "
                + (!countriesJdbc.getAllLocationsByCountry(getCountry).isEmpty() ? "passed" : "failed"));

        LOGGER.info("-----Testing CityDAO-----");
        CityDAO citiesJdbc = new CityDAO();
        City testCity = new City();
        testCity.setCountry(getCountry);
        testCity.setName("testCity");
        citiesJdbc.saveEntity(testCity);
        testCity.setName("nameChange");
        citiesJdbc.updateEntity(testCity);
        testCity = citiesJdbc.getEntityById(testCity.getId());
        LOGGER.info("Testing CityDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testCity.getName().equals("nameChange")) ? "passed" : "failed"));
        citiesJdbc.removeEntityById(testCity.getId());
        testCity = citiesJdbc.getEntityById(testCity.getId());
        LOGGER.info("Testing CountryDAO.deleteById: "
                + ((testCity == null) ? "passed" : "failed"));
        City getCity = citiesJdbc.getAll().get(0);
        LOGGER.info("Testing CityDAO.getAll: "
                + ((getCity != null) ? "passed" : "failed"));
        LOGGER.info("Testing CityDAO.getLocationsByCity: "
                + (!citiesJdbc.getLocationsByCity(getCity).isEmpty() ? "passed" : "failed"));


        LOGGER.info("-----Testing LocationDAO-----");
        LocationDAO locationsJdbc = new LocationDAO();
        Location testLocation = new Location();
        testLocation.setCity(getCity);
        testLocation.setZipCode("12345");
        testLocation.setAddress("Test Address");
        locationsJdbc.saveEntity(testLocation);
        testLocation.setZipCode("54321");
        locationsJdbc.updateEntity(testLocation);
        testLocation = locationsJdbc.getEntityById(testLocation.getId());
        LOGGER.info("Testing LocationDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testLocation.getZipCode().equals("54321")) ? "passed" : "failed"));
        locationsJdbc.removeEntityById(testLocation.getId());
        testLocation = locationsJdbc.getEntityById(testLocation.getId());
        LOGGER.info("Testing LocationDAO.deleteById: "
                + ((testLocation == null) ? "passed" : "failed"));
        Location getLocation = locationsJdbc.getAll().get(0);
        LOGGER.info("Testing CountryDAO.getAll: "
                + ((getLocation != null) ? "passed" : "failed"));

        City tmpCity = new City();
        tmpCity.setName("testCity");
        tmpCity.setCountry(getCountry);
        tmpCity.setCountry(getCountry);
        citiesJdbc.saveEntity(tmpCity);
        locationsJdbc.updateCity(getLocation, tmpCity);
        LOGGER.info("Testing LocationDAO.updateCity: "
                + ((getLocation.getCity().getName().equals("testCity")) ? "passed" : "failed"));
        getLocation.setCity(getCity);
        locationsJdbc.updateCity(getLocation, getCity);


        LOGGER.info("-----Testing BranchDAO-----");
        BranchDAO branchesJdbc = new BranchDAO();
        Branch testBranch = new Branch();
        testBranch.setLocation(getLocation);
        testBranch.setBranchName("Test Branch");
        branchesJdbc.saveEntity(testBranch);
        testBranch.setBranchName("nameChange");
        branchesJdbc.updateEntity(testBranch);
        testBranch = branchesJdbc.getEntityById(testBranch.getId());
        LOGGER.info("Testing BranchDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testBranch.getBranchName().equals("nameChange")) ? "passed" : "failed"));
        branchesJdbc.removeEntityById(testBranch.getId());
        testBranch = branchesJdbc.getEntityById(testBranch.getId());
        LOGGER.info("Testing BranchDAO.deleteById: "
                + ((testBranch == null) ? "passed" : "failed"));
        Branch getBranch = branchesJdbc.getAll().get(0);
        LOGGER.info("Testing BranchDAO.getAll: "
                + ((getBranch != null) ? "passed" : "failed"));
        LOGGER.info("Testing BranchDAO.getAllBranchesByLocationId: "
                + (!branchesJdbc.getAllBranchesByLocationId(getLocation.getId()).isEmpty() ? "passed" : "failed"));

        LOGGER.info("-----Testing AssociateDAO-----");
        AssociateDAO associatesJdbc = new AssociateDAO();
        Associate testAssociate = new Associate();
        testAssociate.setLocation(getLocation);
        testAssociate.setPrimaryName("John Doe");
        testAssociate.setSecondaryName("Doe");
        testAssociate.setDateJoined(new Timestamp(System.currentTimeMillis()));
        testAssociate.setEmail("john.doe@example.com");
        testAssociate.setPhoneNumber("123-456-7890");
        associatesJdbc.saveEntity(testAssociate);
        testAssociate.setPrimaryName("nameChange");
        associatesJdbc.updateEntity(testAssociate);
        testAssociate = associatesJdbc.getEntityById(testAssociate.getId());
        LOGGER.info("Testing AssociateDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testAssociate.getPrimaryName().equals("nameChange")) ? "passed" : "failed"));
        associatesJdbc.removeEntityById(testAssociate.getId());
        testAssociate = associatesJdbc.getEntityById(testAssociate.getId());
        LOGGER.info("Testing AssociateDAO.deleteById: "
                + ((testAssociate == null) ? "passed" : "failed"));
        Associate getAssociate = associatesJdbc.getAll().get(0);
        LOGGER.info("Testing AssociateDAO.getAll: "
                + ((getAssociate != null) ? "passed" : "failed"));
        LOGGER.info("Testing BranchDAO.getAssociatesByLocationId: "
                + (!associatesJdbc.getAllAssociatesByLocationId(getLocation.getId()).isEmpty() ? "passed" : "failed"));

        LOGGER.info("-----Testing PositionDAO-----");
        PositionDAO positionsJdbc = new PositionDAO();
        Position testPosition = new Position();
        testPosition.setPosition("Test Position");
        positionsJdbc.saveEntity(testPosition);
        testPosition.setSalary(6.7);
        positionsJdbc.updateEntity(testPosition);
        testPosition = positionsJdbc.getEntityById(testPosition.getId());
        LOGGER.info("Testing PositionDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testPosition.getSalary() == 6.7) ? "passed" : "failed"));
        positionsJdbc.removeEntityById(testPosition.getId());
        testPosition = positionsJdbc.getEntityById(testPosition.getId());
        LOGGER.info("Testing PositionDAO.deleteById: " +
                ((testPosition == null) ? "passed" : "failed"));
        Position getPosition = positionsJdbc.getAll().get(0);
        LOGGER.info("Testing AssociateDAO.getAll: " +
                ((getPosition != null) ? "passed" : "failed"));
        ArrayList<Branch> branchesWithoutManager = positionsJdbc.getBranchesWithMissingPosition("manager");
        LOGGER.info("Testing AssociateDAO.getBranchesWithMissingPositions(not empty): "
                + (!branchesWithoutManager.isEmpty() ? "passed" : "failed"));
        ArrayList<Branch> branchesWithoutTellers = positionsJdbc.getBranchesWithMissingPosition("teller");
        LOGGER.info("Testing AssociateDAO.getBranchesWithMissingPositions(empty): "
                + (branchesWithoutTellers.isEmpty() ? "passed" : "failed"));


        LOGGER.info("-----Testing StaffDAO-----");
        StaffDAO staffJdbc = new StaffDAO();
        Staff testStaff = new Staff();

        testAssociate = new Associate();
        testAssociate.setLocation(getLocation);
        testAssociate.setPrimaryName("John");
        testAssociate.setSecondaryName("Doe");
        testAssociate.setDateJoined(new Timestamp(System.currentTimeMillis()));
        testAssociate.setEmail("john.doe@example.com");
        testAssociate.setPhoneNumber("123-456-7890");
        associatesJdbc.saveEntity(testAssociate);

        testStaff.setAssociate(testAssociate);
        testStaff.setPosition(getPosition);
        Timestamp prev = new Timestamp(System.currentTimeMillis());
        testStaff.setDateHired(prev);
        staffJdbc.saveEntity(testStaff);
        Timestamp next = new Timestamp(System.currentTimeMillis() - 1000000);
        testStaff.setDateHired(next);
        staffJdbc.updateEntity(testStaff);
        testStaff = staffJdbc.getEntityById(testStaff.getAssociate().getId());
        LOGGER.info("Testing PositionDAO.(updateEntity, saveEntity, getEntityById, updatePosition): "
                + ((testStaff.getDateHired() != prev) ? "passed" : "failed"));
        staffJdbc.removeEntityById(testStaff.getAssociate().getId());
        testStaff = staffJdbc.getEntityById(testStaff.getAssociate().getId());
        LOGGER.info("Testing StaffDAO.deleteById: " +
                ((testStaff == null) ? "passed" : "failed"));
        Staff getStaff = staffJdbc.getAll().get(0);
        LOGGER.info("Testing StaffDAO.getAll: " +
                ((getStaff != null) ? "passed" : "failed"));


        LOGGER.info("-----Testing BranchHasEmployeeDAO-----");
        BranchHasEmployeeDAO branchHasEmployeeDAO = new BranchHasEmployeeDAO();

        testBranch = new Branch();
        testBranch.setLocation(getLocation);
        testBranch.setBranchName("Test Branch");
        branchesJdbc.saveEntity(testBranch);

        testStaff = new Staff();
        testStaff.setPosition(getPosition);
        testStaff.setAssociate(testAssociate);
        testStaff.setDateHired(prev);
        staffJdbc.saveEntity(testStaff);

        BranchHasEmployee testBranchHasEmployee = new BranchHasEmployee();
        testBranchHasEmployee.setBranch(testBranch);
        testBranchHasEmployee.setStaff(getStaff);
        branchHasEmployeeDAO.saveEntity(testBranchHasEmployee);

        testBranchHasEmployee = branchHasEmployeeDAO.getEntityById(getStaff.getAssociate().getId());

        testBranchHasEmployee.setStaff(testStaff);
        branchHasEmployeeDAO.updateEntity(testBranchHasEmployee);
        testBranchHasEmployee = branchHasEmployeeDAO.getEntityById(testStaff.getAssociate().getId());

        LOGGER.info("Testing PositionDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testBranchHasEmployee.getStaff().getAssociate().getId() == testStaff.getAssociate().getId()) ? "passed" : "failed"));
        BranchHasEmployee retrievedBranchHasEmployee = branchHasEmployeeDAO.getEntityById(testStaff.getAssociate().getId());
        LOGGER.info("Testing BranchHasEmployeeDAO.getEntityById: "
                + ((retrievedBranchHasEmployee != null) ? "passed" : "failed"));
        branchHasEmployeeDAO.removeEntityById(testBranchHasEmployee.getStaff().getAssociate().getId());
        retrievedBranchHasEmployee = branchHasEmployeeDAO.getEntityById(testStaff.getAssociate().getId());
        LOGGER.info("Testing BranchHasEmployeeDAO.removeEntityById: "
                + ((retrievedBranchHasEmployee == null) ? "passed" : "failed"));
        BranchHasEmployee getBranchHasEmployee = branchHasEmployeeDAO.getAll().get(0);
        LOGGER.info("Testing BranchHasEmployeeDAO.getAll: "
                + ((getBranchHasEmployee != null) ? "passed" : "failed"));
        LOGGER.info("Testing BranchHasEmployeeDAO.getAllStaffByBranchId: "
                + ((!branchHasEmployeeDAO.getAllStaffByBranchId(testBranch.getId()).isEmpty()) ? "passed" : "failed"));
        branchesJdbc.removeEntityById(testBranch.getId());
        staffJdbc.removeEntityById(testStaff.getAssociate().getId());


        LOGGER.info("-----Testing MerchantDAO-----");
        MerchantDAO merchantDAO = new MerchantDAO();
        Merchant testMerchant = new Merchant();
        testMerchant.setAssociate(testAssociate);
        merchantDAO.saveEntity(testMerchant);
        testMerchant.getAssociate().setSecondaryName("changeName");
        merchantDAO.updateEntity(testMerchant);
        testMerchant = merchantDAO.getEntityById(testMerchant.getAssociate().getId());
        LOGGER.info("Testing MerchantDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testMerchant.getAssociate().getSecondaryName().equals("changeName")) ? "passed" : "failed"));
        merchantDAO.removeEntityById(testMerchant.getAssociate().getId());
        testMerchant = merchantDAO.getEntityById(testMerchant.getAssociate().getId());
        LOGGER.info("Testing MerchantDAO.deleteById: "
                + ((testMerchant == null) ? "passed" : "failed"));
        Merchant getMerchant = merchantDAO.getAll().get(0);
        LOGGER.info("Testing MerchantDAO.getAll: "
                + ((getBranchHasEmployee != null) ? "passed" : "failed"));
        LOGGER.info("Testing MerchantDAO.getCustomerWithTransactions: "
                + (!merchantDAO.getCustomersWithTransactions(getMerchant).isEmpty() ? "passed" : "fail"));

        LOGGER.info("-----Testing CustomerDAO-----");
        CustomerDAO customerDAO = new CustomerDAO();
        Customer testCustomer = new Customer();
        testCustomer.setAssociate(testAssociate);
        testCustomer.setCreditScore(750.0);
        customerDAO.saveEntity(testCustomer);
        double newCreditScore = 800.0;
        testCustomer.setCreditScore(newCreditScore);
        customerDAO.updateEntity(testCustomer);
        testCustomer = customerDAO.getEntityById(testCustomer.getAssociate().getId());
        LOGGER.info("Testing CustomerDAO.(updateEntity, saveEntity, getEntityById, updateCreditScore): "
                + ((testCustomer.getCreditScore() == newCreditScore) ? "passed" : "failed"));
        customerDAO.removeEntityById(testCustomer.getAssociate().getId());
        testCustomer = customerDAO.getEntityById(testCustomer.getAssociate().getId());
        LOGGER.info("Testing CustomerDAO.deleteById: "
                + ((testCustomer == null) ? "passed" : "failed"));
        Customer getCustomer = customerDAO.getAll().get(0);
        LOGGER.info("Testing CustomerDAO.getAll: "
                + ((getCustomer != null) ? "passed" : "failed"));

        LOGGER.info("-----Testing AccountDAO-----");
        AccountDAO accountDAO = new AccountDAO();
        Account testAccount = new Account();
        testAccount.setBranch(getBranch);
        testAccount.setCustomer(getCustomer);
        testAccount.setAmount(1000.0);
        testAccount.setDateCreated(new Timestamp(System.currentTimeMillis()));
        testAccount.setHolds(false);
        accountDAO.saveEntity(testAccount);
        double newAmount = 1500.0;
        testAccount.setAmount(newAmount);
        accountDAO.updateEntity(testAccount);
        testAccount = accountDAO.getEntityById(testAccount.getId());
        LOGGER.info("Testing AccountDAO.(updateEntity, saveEntity, getEntityById, addAmount): "
                + ((testAccount.getAmount() == newAmount) ? "passed" : "failed"));
        accountDAO.removeEntityById(testAccount.getId());
        testAccount = accountDAO.getEntityById(testAccount.getId());
        LOGGER.info("Testing AccountDAO.deleteById: "
                + ((testAccount == null) ? "passed" : "failed"));
        Account getAccount = accountDAO.getAll().get(0);
        LOGGER.info("Testing AccountDAO.getAll: "
                + ((getAccount != null) ? "passed" : "failed"));

        LOGGER.info("-----Testing TransferStatusDAO-----");
        TransferStatusDAO transferStatusDAO = new TransferStatusDAO();
        TransferStatus testTransferStatus = new TransferStatus();
        testTransferStatus.setStatus("Test Status");
        transferStatusDAO.saveEntity(testTransferStatus);
        String newStatus = "changeName";
        testTransferStatus = transferStatusDAO.getEntityById(testTransferStatus.getId());
        testTransferStatus.setStatus(newStatus);
        transferStatusDAO.updateEntity(testTransferStatus);
        testTransferStatus = transferStatusDAO.getEntityById(testTransferStatus.getId());
        LOGGER.info("Testing AccountDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testTransferStatus.getStatus().equals(newStatus)) ? "passed" : "failed"));
        transferStatusDAO.removeEntityById(testTransferStatus.getId());
        testTransferStatus = transferStatusDAO.getEntityById(testTransferStatus.getId());
        LOGGER.info("Testing AccountDAO.deleteById: "
                + ((testTransferStatus == null) ? "passed" : "failed"));
        TransferStatus getTransferStatus = transferStatusDAO.getAll().get(0);
        LOGGER.info("Testing AccountDAO.getAll: "
                + ((getTransferStatus != null) ? "passed" : "failed"));
        LOGGER.info("Testing TransferStatusDAO.getTransactionsByStatusId: "
                + (!transferStatusDAO.getTransactionsByStatusId(getTransferStatus.getId()).isEmpty() ? "passed" : "fail"));


        LOGGER.info("-----Testing TransferDAO-----");
        TransferDAO transferDAO = new TransferDAO();
        Transfer testTransfer = new Transfer();
        testTransfer.setSender(accountDAO.getEntityById(1));
        testTransfer.setReceiver(accountDAO.getEntityById(2));
        testTransfer.setTransferStatus(transferStatusDAO.getEntityById(3));
        testTransfer.setTime(new Timestamp(System.currentTimeMillis()));
        testTransfer.setAmount(500.0);
        transferDAO.saveEntity(testTransfer);
        double newAmount_transfer = 800.0;
        testTransfer.setAmount(newAmount_transfer);
        transferDAO.updateEntity(testTransfer);
        testTransfer = transferDAO.getEntityById(testTransfer.getId());
        LOGGER.info("Testing TransferDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testTransfer.getAmount() == newAmount_transfer) ? "passed" : "failed"));
        transferDAO.removeDeclinedTransfers(testTransfer);
        testTransfer = transferDAO.getEntityById(testTransfer.getId());
        LOGGER.info("Testing TransferDAO.(deleteById, removedDeclinedTransfer): "
                + ((testTransfer == null) ? "passed" : "failed"));
        Transfer getTransfer = transferDAO.getAll().get(0);
        LOGGER.info("Testing TransferDAO.getAll: "
                + ((getTransfer != null) ? "passed" : "failed"));


        LOGGER.info("-----Testing CardDAO-----");
        CardDAO cardDAO = new CardDAO();
        Card testCard = new Card();
        testCard.setAccount(getAccount);
        testCard.setCardNumber("1234567890123456");
        testCard.setExpirationDate(Timestamp.valueOf("2023-12-31 23:59:59"));
        testCard.setCvv("123");
        cardDAO.saveEntity(testCard);
        String newCardNumber = "9876543210987654";
        testCard.setCardNumber(newCardNumber);
        cardDAO.updateEntity(testCard);
        testCard = cardDAO.getEntityById(testCard.getId());
        LOGGER.info("Testing CardDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testCard.getCardNumber().equals(newCardNumber)) ? "passed" : "failed"));
        cardDAO.removeEntityById(testCard.getId());
        testCard = cardDAO.getEntityById(testCard.getId());
        LOGGER.info("Testing CardDAO.(deleteById, removedDeclinedTransfer): "
                + ((testTransfer == null) ? "passed" : "failed"));
        Card getCard = cardDAO.getAll().get(0);
        LOGGER.info("Testing CardDAO.getAll: "
                + ((getCard != null) ? "passed" : "failed"));
        LOGGER.info("Testing CardDAO.getAllTransactionsByCard: "
                + (!cardDAO.getAllTransactionsByCard(getCard).isEmpty() ? "passed" : "fail"));

        LOGGER.info("-----Testing TransactionDAO-----");
        TransactionDAO transactionDAO = new TransactionDAO();
        Transaction testTransaction = new Transaction();
        testTransaction.setCard(getCard);
        testTransaction.setMerchant(getMerchant);
        testTransaction.setTransferStatus(getTransferStatus);
        testTransaction.setTime(new Timestamp(System.currentTimeMillis()));
        testTransaction.setAmount(100.0);
        transactionDAO.saveEntity(testTransaction);
        double newAmount_transaction = 200.0;
        testTransaction.setAmount(newAmount_transaction);
        transactionDAO.updateEntity(testTransaction);
        testTransaction = transactionDAO.getEntityById(testTransaction.getId());
        LOGGER.info("Testing TransactionDAO.(updateEntity, saveEntity, getEntityById, updateStatus): "
                + ((testTransaction.getAmount() == newAmount_transaction) ? "passed" : "failed"));
        transactionDAO.removeEntityById(testTransaction.getId());
        testTransaction = transactionDAO.getEntityById(testTransaction.getId());
        LOGGER.info("Testing TransactionDAO.(deleteById, removedDeclinedTransfer): "
                + ((testTransaction == null) ? "passed" : "failed"));
        Transaction getTransaction = transactionDAO.getAll().get(0);
        LOGGER.info("Testing TransactionDAO.getAll: "
                + (getTransaction != null ? "passed" : "fail"));

        associatesJdbc.removeEntityById(testAssociate.getId());
    }
}

