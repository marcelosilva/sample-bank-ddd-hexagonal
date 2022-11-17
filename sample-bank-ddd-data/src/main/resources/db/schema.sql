CREATE TABLE account (
    id UUID NOT NULL PRIMARY KEY,
    balance NUMERIC NOT NULL
);

CREATE TABLE account_transaction (
    id UUID NOT NULL PRIMARY KEY,
    account_id UUID not null,
    amount NUMERIC NOT NULL,
    description VARCHAR(255),
    foreign key (account_id) references account(id)
);