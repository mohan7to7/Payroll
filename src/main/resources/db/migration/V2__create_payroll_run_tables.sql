CREATE TABLE payroll_run (
    id BIGSERIAL PRIMARY KEY,
    period_start DATE NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE payroll_transaction (
    id BIGSERIAL PRIMARY KEY,
    payroll_run_id BIGINT NOT NULL REFERENCES payroll_run(id),
    employee_id BIGINT NOT NULL,
    employee_name VARCHAR(255) NOT NULL,
    gross_amount DECIMAL(19, 2) NOT NULL,
    CONSTRAINT uq_payroll_transaction_employee UNIQUE (payroll_run_id, employee_id)
);
