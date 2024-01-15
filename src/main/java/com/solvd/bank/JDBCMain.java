package com.solvd.bank;

import com.solvd.bank.domain.*;
import com.solvd.bank.persistence.*;
import com.solvd.bank.persistence.jdbcimpl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Timestamp;
import java.util.ArrayList;


public class JDBCMain {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        ICountryDAO countriesDAO = new CountryDAO();
        ICityDAO citiesDAO = new CityDAO();
        ILocationDAO locationDAO = new LocationDAO();
        IBranchDAO branchDAO = new BranchDAO();
        IAssociateDAO associateDAO = new AssociateDAO();
        IPositionDAO positionDOA = new PositionDAO();
        IStaffDAO staffDAO = new StaffDAO();
        IBranchHasEmployeeDAO branchHasEmployeeDAO = new BranchHasEmployeeDAO();
        IMerchantDAO merchantDAO = new MerchantDAO();
        ICustomerDAO customerDAO = new CustomerDAO();
        IAccountDAO accountDAO = new AccountDAO();
        ITransferStatusDAO transferStatusDAO = new TransferStatusDAO();
        ITransferDAO transferDAO = new TransferDAO();
        ICardDAO cardDAO = new CardDAO();
        ITransactionDAO transactionDAO = new TransactionDAO();

        LOGGER.info("-----Testing CountryDAO-----");
        Country testCountry = new Country();
        testCountry.setName("testCountry");
        countriesDAO.saveEntity(testCountry);
        testCountry.setName("nameChange");
        countriesDAO.updateEntity(testCountry);
        testCountry = countriesDAO.getEntityById(testCountry.getId());
        LOGGER.info("Testing CountryDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testCountry.getName().equals("nameChange")) ? "passed" : "failed"));
        countriesDAO.removeEntityById(testCountry.getId());
        testCountry = countriesDAO.getEntityById(testCountry.getId());
        LOGGER.info("Testing CountryDAO.deleteById: "
                + ((testCountry == null) ? "passed" : "failed"));
        Country getCountry = countriesDAO.getAll().get(0);
        LOGGER.info("Testing CountryDAO.getAll: "
                + ((getCountry != null) ? "passed" : "failed"));
        LOGGER.info("Testing CountryDAO.getAllLocationsByCountry: "
                + (!countriesDAO.getAllLocationsByCountry(getCountry).isEmpty() ? "passed" : "failed"));

        LOGGER.info("-----Testing CityDAO-----");
        City testCity = new City();
        testCity.setCountry(getCountry);
        testCity.setName("testCity");
        citiesDAO.saveEntity(testCity);
        testCity.setName("nameChange");
        citiesDAO.updateEntity(testCity);
        testCity = citiesDAO.getEntityById(testCity.getId());
        LOGGER.info("Testing CityDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testCity.getName().equals("nameChange")) ? "passed" : "failed"));
        citiesDAO.removeEntityById(testCity.getId());
        testCity = citiesDAO.getEntityById(testCity.getId());
        LOGGER.info("Testing CountryDAO.deleteById: "
                + ((testCity == null) ? "passed" : "failed"));
        City getCity = citiesDAO.getAll().get(0);
        LOGGER.info("Testing CityDAO.getAll: "
                + ((getCity != null) ? "passed" : "failed"));
        LOGGER.info("Testing CityDAO.getLocationsByCity: "
                + (!citiesDAO.getLocationsByCity(getCity).isEmpty() ? "passed" : "failed"));

        LOGGER.info("-----Testing LocationDAO-----");
        Location testLocation = new Location();
        testLocation.setCity(getCity);
        testLocation.setZipCode("12345");
        testLocation.setAddress("Test Address");
        locationDAO.saveEntity(testLocation);
        testLocation.setZipCode("54321");
        locationDAO.updateEntity(testLocation);
        testLocation = locationDAO.getEntityById(testLocation.getId());
        LOGGER.info("Testing LocationDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testLocation.getZipCode().equals("54321")) ? "passed" : "failed"));
        locationDAO.removeEntityById(testLocation.getId());
        testLocation = locationDAO.getEntityById(testLocation.getId());
        LOGGER.info("Testing LocationDAO.deleteById: "
                + ((testLocation == null) ? "passed" : "failed"));
        Location getLocation = locationDAO.getAll().get(0);
        LOGGER.info("Testing CountryDAO.getAll: "
                + ((getLocation != null) ? "passed" : "failed"));

        City tmpCity = new City();
        tmpCity.setName("testCity");
        tmpCity.setCountry(getCountry);
        citiesDAO.saveEntity(tmpCity);
        getLocation.setCity(tmpCity);
        locationDAO.updateCity(getLocation);
        LOGGER.info("Testing LocationDAO.updateCity: "
                + ((getLocation.getCity().getName().equals("testCity")) ? "passed" : "failed"));
        getLocation.setCity(getCity);
        locationDAO.updateCity(getLocation);

        LOGGER.info("-----Testing BranchDAO-----");
        Branch testBranch = new Branch();
        testBranch.setLocation(getLocation);
        testBranch.setBranchName("Test Branch");
        branchDAO.saveEntity(testBranch);
        testBranch.setBranchName("nameChange");
        branchDAO.updateEntity(testBranch);
        testBranch = branchDAO.getEntityById(testBranch.getId());
        LOGGER.info("Testing BranchDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testBranch.getBranchName().equals("nameChange")) ? "passed" : "failed"));
        branchDAO.removeEntityById(testBranch.getId());
        testBranch = branchDAO.getEntityById(testBranch.getId());
        LOGGER.info("Testing BranchDAO.deleteById: "
                + ((testBranch == null) ? "passed" : "failed"));
        Branch getBranch = branchDAO.getAll().get(0);
        LOGGER.info("Testing BranchDAO.getAll: "
                + ((getBranch != null) ? "passed" : "failed"));
        LOGGER.info("Testing BranchDAO.getAllBranchesByLocationId: "
                + (!branchDAO.getAllBranchesByLocationId(getLocation.getId()).isEmpty() ? "passed" : "failed"));

        LOGGER.info("-----Testing AssociateDAO-----");
        Associate testAssociate = new Associate();
        testAssociate.setLocation(getLocation);
        testAssociate.setPrimaryName("John Doe");
        testAssociate.setSecondaryName("Doe");
        testAssociate.setDateJoined(new Timestamp(System.currentTimeMillis()));
        testAssociate.setEmail("john.doe@example.com");
        testAssociate.setPhoneNumber("123-456-7890");
        associateDAO.saveEntity(testAssociate);
        testAssociate.setPrimaryName("nameChange");
        associateDAO.updateEntity(testAssociate);
        testAssociate = associateDAO.getEntityById(testAssociate.getId());
        LOGGER.info("Testing AssociateDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testAssociate.getPrimaryName().equals("nameChange")) ? "passed" : "failed"));
        associateDAO.removeEntityById(testAssociate.getId());
        testAssociate = associateDAO.getEntityById(testAssociate.getId());
        LOGGER.info("Testing AssociateDAO.deleteById: "
                + ((testAssociate == null) ? "passed" : "failed"));
        Associate getAssociate = associateDAO.getAll().get(0);
        LOGGER.info("Testing AssociateDAO.getAll: "
                + ((getAssociate != null) ? "passed" : "failed"));
        LOGGER.info("Testing BranchDAO.getAssociatesByLocationId: "
                + (!associateDAO.getAllAssociatesByLocationId(getLocation.getId()).isEmpty() ? "passed" : "failed"));

        LOGGER.info("-----Testing PositionDAO-----");
        Position testPosition = new Position();
        testPosition.setPosition("Test Position");
        positionDOA.saveEntity(testPosition);
        testPosition.setSalary(6.7);
        positionDOA.updateEntity(testPosition);
        testPosition = positionDOA.getEntityById(testPosition.getId());
        LOGGER.info("Testing PositionDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testPosition.getSalary() == 6.7) ? "passed" : "failed"));
        positionDOA.removeEntityById(testPosition.getId());
        testPosition = positionDOA.getEntityById(testPosition.getId());
        LOGGER.info("Testing PositionDAO.deleteById: " +
                ((testPosition == null) ? "passed" : "failed"));
        Position getPosition = positionDOA.getAll().get(0);
        LOGGER.info("Testing AssociateDAO.getAll: " +
                ((getPosition != null) ? "passed" : "failed"));
        ArrayList<Branch> branchesWithoutManager = positionDOA.getBranchesWithMissingPosition("manager");
        LOGGER.info("Testing AssociateDAO.getBranchesWithMissingPositions(not empty): "
                + (!branchesWithoutManager.isEmpty() ? "passed" : "failed"));
        ArrayList<Branch> branchesWithoutTellers = positionDOA.getBranchesWithMissingPosition("teller");
        LOGGER.info("Testing AssociateDAO.getBranchesWithMissingPositions(empty): "
                + (branchesWithoutTellers.isEmpty() ? "passed" : "failed"));

        LOGGER.info("-----Testing StaffDAO-----");
        Staff testStaff = new Staff();
        testAssociate = new Associate();
        testAssociate.setLocation(getLocation);
        testAssociate.setPrimaryName("John");
        testAssociate.setSecondaryName("Doe");
        testAssociate.setDateJoined(new Timestamp(System.currentTimeMillis()));
        testAssociate.setEmail("john.doe@example.com");
        testAssociate.setPhoneNumber("123-456-7890");
        associateDAO.saveEntity(testAssociate);

        testStaff.setAssociate(testAssociate);
        testStaff.setPosition(getPosition);
        Timestamp prev = new Timestamp(System.currentTimeMillis());
        testStaff.setDateHired(prev);
        staffDAO.saveEntity(testStaff);
        Timestamp next = new Timestamp(System.currentTimeMillis() - 1000000);
        testStaff.setDateHired(next);
        staffDAO.updateEntity(testStaff);
        testStaff = staffDAO.getEntityById(testStaff.getAssociate().getId());
        LOGGER.info("Testing PositionDAO.(updateEntity, saveEntity, getEntityById, updatePosition): "
                + ((testStaff.getDateHired() != prev) ? "passed" : "failed"));
        staffDAO.removeEntityById(testStaff.getAssociate().getId());
        testStaff = staffDAO.getEntityById(testStaff.getAssociate().getId());
        LOGGER.info("Testing StaffDAO.deleteById: " +
                ((testStaff == null) ? "passed" : "failed"));
        Staff getStaff = staffDAO.getAll().get(0);
        LOGGER.info("Testing StaffDAO.getAll: " +
                ((getStaff != null) ? "passed" : "failed"));

        LOGGER.info("-----Testing BranchHasEmployeeDAO-----");
        testBranch = new Branch();
        testBranch.setLocation(getLocation);
        testBranch.setBranchName("Test Branch");
        branchDAO.saveEntity(testBranch);

        testStaff = new Staff();
        testStaff.setPosition(getPosition);
        testStaff.setAssociate(testAssociate);
        testStaff.setDateHired(prev);
        staffDAO.saveEntity(testStaff);

        BranchHasEmployee testBranchHasEmployee = new BranchHasEmployee();
        testBranchHasEmployee.setBranch(testBranch);
        testBranchHasEmployee.setStaff(testStaff);
        branchHasEmployeeDAO.saveEntity(testBranchHasEmployee);
        testBranchHasEmployee.getBranch().setBranchName("nameChange");
        branchHasEmployeeDAO.updateEntity(testBranchHasEmployee);
        testBranchHasEmployee = branchHasEmployeeDAO.getEntityById(testStaff.getAssociate().getId());
        LOGGER.info("Testing PositionDAO.(updateEntity, saveEntity, getEntityById): "
                + (testBranchHasEmployee.getBranch().getBranchName().equals("nameChange") ? "passed" : "failed"));

        branchHasEmployeeDAO.removeEntityById(testBranchHasEmployee.getStaff().getAssociate().getId());
        testBranchHasEmployee = branchHasEmployeeDAO.getEntityById(testStaff.getAssociate().getId());
        LOGGER.info("Testing BranchHasEmployeeDAO.removeEntityById: "
                + ((testBranchHasEmployee == null) ? "passed" : "failed"));
        BranchHasEmployee getBranchHasEmployee = branchHasEmployeeDAO.getAll().get(0);
        LOGGER.info("Testing BranchHasEmployeeDAO.getAll: "
                + ((getBranchHasEmployee != null) ? "passed" : "failed"));
        LOGGER.info("Testing BranchHasEmployeeDAO.getAllStaffByBranchId: "
                + ((!branchHasEmployeeDAO.getAllStaffByBranchId(getBranch.getId()).isEmpty()) ? "passed" : "failed"));
        branchDAO.removeEntityById(testBranch.getId());
        staffDAO.removeEntityById(testStaff.getAssociate().getId());

        LOGGER.info("-----Testing MerchantDAO-----");
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
                + ((getMerchant != null) ? "passed" : "failed"));
        LOGGER.info("Testing MerchantDAO.getCustomerWithTransactions: "
                + (!merchantDAO.getCustomersWithTransactions(getMerchant).isEmpty() ? "passed" : "fail"));

        LOGGER.info("-----Testing CustomerDAO-----");
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
        Transfer testTransfer = new Transfer();
        testTransfer.setSender(accountDAO.getEntityById(1));
        testTransfer.setReceiver(accountDAO.getEntityById(2));
        testTransfer.setTransferStatus(transferStatusDAO.getEntityById(3));
        testTransfer.setTransferTime(new Timestamp(System.currentTimeMillis()));
        testTransfer.setAmount(500.0);
        transferDAO.saveEntity(testTransfer);
        double newAmount_transfer = 800.0;
        testTransfer.setAmount(newAmount_transfer);
        transferDAO.updateEntity(testTransfer);
        testTransfer = transferDAO.getEntityById(testTransfer.getId());
        LOGGER.info("Testing TransferDAO.(updateEntity, saveEntity, getEntityById): "
                + ((testTransfer.getAmount() == newAmount_transfer) ? "passed" : "failed"));
        transferDAO.removeDeclinedTransfers();
        testTransfer = transferDAO.getEntityById(testTransfer.getId());
        LOGGER.info("Testing TransferDAO.(deleteById, removedDeclinedTransfer): "
                + ((testTransfer == null) ? "passed" : "failed"));
        Transfer getTransfer = transferDAO.getAll().get(0);
        LOGGER.info("Testing TransferDAO.getAll: "
                + ((getTransfer != null) ? "passed" : "failed"));

        LOGGER.info("-----Testing CardDAO-----");
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
                + ((testCard == null) ? "passed" : "failed"));
        Card getCard = cardDAO.getAll().get(0);
        LOGGER.info("Testing CardDAO.getAll: "
                + ((getCard != null) ? "passed" : "failed"));
        LOGGER.info("Testing CardDAO.getAllTransactionsByCard: "
                + (!cardDAO.getAllTransactionsByCard(getCard).isEmpty() ? "passed" : "fail"));

        LOGGER.info("-----Testing TransactionDAO-----");
        Transaction testTransaction = new Transaction();
        testTransaction.setCard(getCard);
        testTransaction.setMerchant(getMerchant);
        testTransaction.setTransferStatus(getTransferStatus);
        testTransaction.setTransactionTime(new Timestamp(System.currentTimeMillis()));
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
        associateDAO.removeEntityById(testAssociate.getId());
    }
}
