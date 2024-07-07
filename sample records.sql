INSERT INTO category (category_id, category_name) VALUES
(1, 'Technology'),
(2, 'Construction'),
(3, 'Finance'),
(4, 'Healthcare'),
(5, 'Education');

INSERT INTO customer (customer_id, customer_name, category_id, address, country, type, contact_details) VALUES
('CUST001', 'ABC Technologies', 1, '123 Tech Street', 'USA', 'Customer', '123-456-7890'),
('CUST002', 'XYZ Construction', 2, '456 Builder Lane', 'UK', 'Partner', '987-654-3210'),
('CUST003', 'Finance Solutions Inc.', 3, '789 Finance Ave', 'Canada', 'Customer', '555-123-4567'),
('CUST004', 'Healthcare Innovations', 4, '321 Health Blvd', 'Australia', 'Agent', '111-222-3333'),
('CUST005', 'Global Education Group', 5, '555 Learn Lane', 'India', 'Customer', '999-888-7777'),
('CUST006', 'Retail Connections Ltd.', 1, '777 Shopper St', 'Germany', 'Partner', '444-555-6666'),
('CUST007', 'Auto Parts Suppliers', 2, '999 Car Road', 'Japan', 'Customer', '777-999-1111'),
('CUST008', 'Luxury Hotel Group', 3, '111 Resort Ave', 'France', 'Partner', '222-333-4444'),
('CUST009', 'Media Productions Inc.', 4, '222 Studio Lane', 'Spain', 'Customer', '333-444-5555'),
('CUST010', 'Telecom Services Ltd.', 5, '333 Connect Ave', 'Brazil', 'Agent', '666-777-8888'),
('CUST011', 'Energy Solutions Corp.', 1, '444 Power St', 'Mexico', 'Customer', '888-999-0000'),
('CUST012', 'Transport Logistics', 2, '666 Transport Blvd', 'Russia', 'Partner', '111-222-3333'),
('CUST013', 'Real Estate Developers', 3, '888 Realty Ave', 'South Africa', 'Customer', '222-333-4444'),
('CUST014', 'Manufacturing Innovators', 4, '000 Factory Lane', 'China', 'Partner', '444-555-6666'),
('CUST015', 'Agri Farms Ltd.', 5, '111 Farm Road', 'Argentina', 'Customer', '777-888-9999'),
('CUST016', 'Tech Solutions Ltd.', 1, '222 Tech Lane', 'USA', 'Customer', '999-111-2222'),
('CUST017', 'Building Innovations Inc.', 2, '333 Builder St', 'UK', 'Partner', '888-222-3333'),
('CUST018', 'Finance Consultants Ltd.', 3, '444 Finance Road', 'Canada', 'Customer', '777-333-4444'),
('CUST019', 'Healthcare Services Group', 4, '555 Health Ave', 'Australia', 'Agent', '666-444-5555'),
('CUST020', 'Global Education Solutions', 5, '666 Learn Lane', 'India', 'Customer', '555-777-8888');

INSERT INTO project (ProjectID, Description, customer_id, EndClientDetail, EndClientCountry, PONo, PODate, POInitialValue, GSTPercent, PORev1Value, PORev1Date, PORev2Value, PORev2Date, PORev3Value, PORev3Date, PORev4Value, PORev4Date, Currency, PaymentTerms, ProjectLead, ExpectedCompletionDate, ActualCompletionDate, CompletionPercent) VALUES
('PROJ001', 'Tech Solutions Upgrade', 'CUST001', 'Global Tech Solutions', 'USA', 'PO123', '2024-01-15', 10000.00, 18.5, 12000.00, '2024-02-28', 15000.00, '2024-04-15', 18000.00, '2024-05-30', 20000.00, '2024-07-15', 'USD', 'Net 30', 'John Doe', '2024-08-31', '2024-09-25', 80.0),
('PROJ002', 'Construction Project A', 'CUST002', 'Urban Construction Ltd.', 'UK', 'PO456', '2024-02-10', 15000.00, 15.0, 18000.00, '2024-03-31', 20000.00, '2024-05-15', 22000.00, '2024-06-30', 24000.00, '2024-08-15', 'GBP', 'Net 45', 'Jane Smith', '2024-09-30', '2024-10-20', 75.0),
('PROJ003', 'Financial Software Upgrade', 'CUST003', 'Finance Tech Solutions', 'Canada', 'PO789', '2024-03-20', 12000.00, 12.0, 14000.00, '2024-05-15', 16000.00, '2024-07-01', 18000.00, '2024-08-15', 20000.00, '2024-10-01', 'CAD', 'Net 60', 'David Brown', '2024-11-15', '2024-12-10', 90.0),
('PROJ004', 'Healthcare System Integration', 'CUST004', 'Healthcare Solutions Inc.', 'Australia', 'PO321', '2024-04-05', 18000.00, 10.0, 20000.00, '2024-06-30', 22000.00, '2024-08-15', 24000.00, '2024-09-30', 26000.00, '2024-11-15', 'AUD', 'Net 45', 'Emily Johnson', '2025-01-31', '2025-02-20', 85.0),
('PROJ005', 'Education Platform Development', 'CUST005', 'Global Education Solutions', 'India', 'PO555', '2024-05-15', 15000.00, 18.0, 18000.00, '2024-07-31', 21000.00, '2024-09-15', 24000.00, '2024-10-31', 27000.00, '2024-12-15', 'INR', 'Net 30', 'Michael Clark', '2025-01-31', '2025-03-01', 70.0),
('PROJ006', 'Retail Store Expansion', 'CUST006', 'Retail Connections Ltd.', 'Germany', 'PO777', '2024-06-01', 20000.00, 20.0, 24000.00, '2024-08-15', 28000.00, '2024-10-01', 32000.00, '2024-11-15', 36000.00, '2025-01-01', 'EUR', 'Net 60', 'Sarah Wilson', '2025-02-28', '2025-04-15', 75.0),
('PROJ007', 'Automotive Parts Distribution', 'CUST007', 'Auto Parts Distributors', 'Japan', 'PO999', '2024-07-10', 25000.00, 15.0, 28000.00, '2024-09-30', 31000.00, '2024-11-15', 34000.00, '2025-01-31', 37000.00, '2025-03-15', 'JPY', 'Net 45', 'Chris Lee', '2025-04-30', '2025-06-01', 80.0),
('PROJ008', 'Luxury Hotel Renovation', 'CUST008', 'Luxury Hotel Group', 'France', 'PO111', '2024-08-15', 30000.00, 10.0, 32000.00, '2024-10-31', 35000.00, '2024-12-15', 38000.00, '2025-01-31', 40000.00, '2025-03-15', 'EUR', 'Net 30', 'Anna Brown', '2025-04-30', '2025-06-15', 85.0),
('PROJ009', 'Media Advertising Campaign', 'CUST009', 'Media Productions Inc.', 'Spain', 'PO222', '2024-09-01', 18000.00, 12.5, 20000.00, '2024-11-15', 22000.00, '2025-01-01', 24000.00, '2025-02-15', 26000.00, '2025-04-01', 'EUR', 'Net 60', 'James Miller', '2025-05-31', '2025-07-01', 70.0),
('PROJ010', 'Telecom Network Upgrade', 'CUST010', 'Telecom Services Ltd.', 'Brazil', 'PO333', '2024-10-15', 22000.00, 18.0, 25000.00, '2024-12-31', 28000.00, '2025-02-15', 31000.00, '2025-03-31', 34000.00, '2025-05-15', 'BRL', 'Net 45', 'Emma Johnson', '2025-06-30', '2025-08-01', 80.0),
('PROJ011', 'Energy Efficiency Program', 'CUST011', 'Energy Solutions Corp.', 'Mexico', 'PO444', '2024-11-01', 28000.00, 20.0, 32000.00, '2025-01-15', 36000.00, '2025-03-01', 40000.00, '2025-04-15', 44000.00, '2025-06-01', 'MXN', 'Net 60', 'Matthew Davis', '2025-07-31', '2025-09-15', 85.0),
('PROJ012', 'Transportation Fleet Upgrade', 'CUST012', 'Transport Logistics', 'Russia', 'PO666', '2025-01-15', 25000.00, 15.0, 28000.00, '2025-03-31', 31000.00, '2025-05-15', 34000.00, '2025-06-30', 37000.00, '2025-08-15', 'RUB', 'Net 45', 'Olivia Smith', '2025-09-30', '2025-11-01', 75.0),
('PROJ013', 'Real Estate Development Project', 'CUST013', 'Real Estate Developers', 'Africa', 'PO888', '2025-02-01', 30000.00, 18.0, 35000.00, '2025-04-15', 40000.00, '2025-06-30', 45000.00, '2025-08-15', 50000.00, '2025-10-31', 'ZAR', 'Net 60', 'Sophia White', '2025-12-31', '2026-02-01', 80.0),
('PROJ014', 'Manufacturing Plant Expansion', 'CUST014', 'Manufacturing Innovators', 'China', 'PO000', '2025-03-15', 28000.00, 12.0, 30000.00, '2025-05-31', 32000.00, '2025-07-15', 34000.00, '2025-08-31', 36000.00, '2025-10-15', 'CNY', 'Net 30', 'William Brown', '2025-11-30', '2026-01-01', 70.0),
('PROJ015', 'Agricultural Innovation Project', 'CUST015', 'AgroTech Solutions', 'India', 'PO333', '2025-04-01', 20000.00, 15.0, 22000.00, '2025-06-15', 24000.00, '2025-07-31', 26000.00, '2025-09-15', 28000.00, '2025-10-31', 'INR', 'Net 45', 'Ethan Wilson', '2025-12-31', '2026-02-01', 75.0);

INSERT INTO invoice (customer_id, ProjectID, po_no, invoice_no, invoice_date, description, invoice_type, inv_amount_in_euro, inv_amount_in_usd, inv_amount_in_inr, gst_amount, tds_deducted, retention_amount, amount_received, amount_received_in_inr, received_date, firc_details) VALUES
('CUST001', 'PROJ001', 'PO123', 'INV001', '2024-02-28', 'Milestone 1 Payment', 'Milestone', 8000.00, 9000.00, 700000.00, 1500.00, 500.00, 1000.00, 7500.00, 650000.00, '2024-03-15', 'FIRC001'),
('CUST001', 'PROJ001', 'PO123', 'INV002', '2024-04-15', 'Milestone 2 Payment', 'Milestone', 10000.00, 11000.00, 850000.00, 1800.00, 600.00, 1200.00, 9500.00, 800000.00, '2024-04-30', 'FIRC002'),
('CUST001', 'PROJ001', 'PO123', 'INV003', '2024-05-30', 'Final Payment', 'Milestone', 12000.00, 13000.00, 1000000.00, 2000.00, 700.00, 1500.00, 11000.00, 950000.00, '2024-06-15', 'FIRC003'),
('CUST002', 'PROJ002', 'PO456', 'INV004', '2024-03-31', 'Project Start Payment', 'Milestone', 15000.00, 16000.00, 1200000.00, 2500.00, 800.00, 1800.00, 14000.00, 1150000.00, '2024-04-15', 'FIRC004'),
('CUST002', 'PROJ002', 'PO456', 'INV005', '2024-05-15', 'Project Mid-Phase Payment', 'Milestone', 18000.00, 19000.00, 1400000.00, 3000.00, 1000.00, 2000.00, 17000.00, 1400000.00, '2024-05-30', 'FIRC005'),
('CUST002', 'PROJ002', 'PO456', 'INV006', '2024-06-30', 'Project Completion Payment', 'Milestone', 20000.00, 21000.00, 1600000.00, 3500.00, 1200.00, 2500.00, 19000.00, 1550000.00, '2024-07-15', 'FIRC006'),
('CUST003', 'PROJ003', 'PO789', 'INV007', '2024-05-15', 'Project Start Payment', 'Milestone', 12000.00, 13000.00, 1000000.00, 1500.00, 500.00, 1000.00, 11000.00, 950000.00, '2024-05-31', 'FIRC007'),
('CUST003', 'PROJ003', 'PO789', 'INV008', '2024-07-01', 'Project Mid-Phase Payment', 'Milestone', 14000.00, 15000.00, 1200000.00, 1800.00, 600.00, 1200.00, 13000.00, 1100000.00, '2024-07-15', 'FIRC008'),
('CUST003', 'PROJ003', 'PO789', 'INV009', '2024-08-15', 'Project Completion Payment', 'Milestone', 16000.00, 17000.00, 1400000.00, 2000.00, 700.00, 1500.00, 15000.00, 1250000.00, '2024-08-31', 'FIRC009'),
('CUST004', 'PROJ004', 'PO321', 'INV010', '2024-06-30', 'Project Start Payment', 'Milestone', 18000.00, 19000.00, 1500000.00, 2500.00, 800.00, 1800.00, 17000.00, 1400000.00, '2024-07-15', 'FIRC010'),
('CUST004', 'PROJ004', 'PO321', 'INV011', '2024-08-15', 'Project Mid-Phase Payment', 'Milestone', 20000.00, 21000.00, 1600000.00, 3000.00, 1000.00, 2000.00, 19000.00, 1550000.00, '2024-08-31', 'FIRC011'),
('CUST004', 'PROJ004', 'PO321', 'INV012', '2024-09-30', 'Project Completion Payment', 'Milestone', 22000.00, 23000.00, 1800000.00, 3500.00, 1200.00, 2500.00, 21000.00, 1750000.00, '2024-10-15', 'FIRC012'),
('CUST005', 'PROJ005', 'PO654', 'INV013', '2024-08-15', 'Phase 1 Payment', 'Expense', 16000.00, 17000.00, 1400000.00, 2000.00, 700.00, 1500.00, 15000.00, 1250000.00, '2024-08-31', 'FIRC013'),
('CUST005', 'PROJ005', 'PO654', 'INV014', '2024-09-30', 'Phase 2 Payment', 'Expense', 18000.00, 19000.00, 1500000.00, 2500.00, 800.00, 1800.00, 17000.00, 1400000.00, '2024-10-15', 'FIRC014'),
('CUST005', 'PROJ005', 'PO654', 'INV015', '2024-11-15', 'Final Payment', 'Expense', 20000.00, 21000.00, 1600000.00, 3000.00, 1000.00, 2000.00, 19000.00, 1550000.00, '2024-11-30', 'FIRC015'),
('CUST006', 'PROJ006', 'PO987', 'INV016', '2024-09-30', 'Project Start Payment', 'Milestone', 22000.00, 23000.00, 1800000.00, 2500.00, 800.00, 1800.00, 21000.00, 1750000.00, '2024-10-15', 'FIRC016'),
('CUST006', 'PROJ006', 'PO987', 'INV017', '2024-11-15', 'Project Mid-Phase Payment', 'Milestone', 24000.00, 25000.00, 2000000.00, 3000.00, 1000.00, 2000.00, 23000.00, 1900000.00, '2024-11-30', 'FIRC017'),
('CUST006', 'PROJ006', 'PO987', 'INV018', '2025-01-01', 'Project Completion Payment', 'Milestone', 26000.00, 27000.00, 2200000.00, 3500.00, 1200.00, 2500.00, 25000.00, 2050000.00, '2025-01-15', 'FIRC018'),
('CUST007', 'PROJ007', 'PO345', 'INV019', '2024-10-15', 'Phase 1 Payment', 'Expense', 18000.00, 19000.00, 1500000.00, 2000.00, 700.00, 1500.00, 17000.00, 1400000.00, '2024-10-31', 'FIRC019'),
('CUST007', 'PROJ007', 'PO345', 'INV020', '2024-11-30', 'Phase 2 Payment', 'Expense', 20000.00, 21000.00, 1600000.00, 2500.00, 800.00, 1800.00, 19000.00, 1550000.00, '2024-12-15', 'FIRC020'),
('CUST007', 'PROJ007', 'PO345', 'INV021', '2025-01-15', 'Final Payment', 'Expense', 22000.00, 23000.00, 1800000.00, 3000.00, 1000.00, 2000.00, 21000.00, 1750000.00, '2025-01-31', 'FIRC021' );

