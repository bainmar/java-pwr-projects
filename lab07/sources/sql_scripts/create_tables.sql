CREATE TABLE Client(
	ID int(11) NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    surname varchar(50) NOT NULL,
    clientNumber int(11) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE PriceList(
	ID int(11) NOT NULL auto_increment,
    priceType varchar(50) NOT NULL,
    serviceName varchar(50) NOT NULL,
    clientID int(11),
    PRIMARY KEY (ID),
    foreign key (clientID) references Client (ID)
        on update cascade
        on delete set NULL
);

CREATE TABLE Installation(
	ID int(11) NOT NULL auto_increment,
    address varchar(50) not null,
    routerNumber int(11) not null,
    serviceType varchar(50) not null,
    clientID int(11),
    primary key (ID),
    foreign key (clientID) references Client (ID)
		on update cascade
        on delete set null
);

CREATE TABLE Charge(
	ID int(11) NOT NULL auto_increment,
    paymentSchedule date NOT NULL,
    amountToPay float NOT NULL,
    installationID int(11),
    primary key (ID),
    foreign key (installationID) references Installation(ID)
		on update cascade
        on delete set null
);
    
CREATE TABLE Payment(
	ID int(11) not null auto_increment,
    paymentDate date not null,
    depositAmount float not null,
    installationID int(11),
    primary key(ID),
    foreign key (installationID) references Installation(ID)
		on update cascade
        on delete set null
);



select amountToPay
from Client,Installation,Charge
where Client.ID=Installation.clientID 
and
Installation.ID=Charge.installationID
and Client.ID=5;
