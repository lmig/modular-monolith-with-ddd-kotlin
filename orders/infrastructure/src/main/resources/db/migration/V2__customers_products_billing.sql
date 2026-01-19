-- Customers
CREATE TABLE IF NOT EXISTS customers (
  id UUID PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255),
  created_at TIMESTAMP WITH TIME ZONE
);

-- Products
CREATE TABLE IF NOT EXISTS products (
  id UUID PRIMARY KEY,
  name VARCHAR(255),
  price BIGINT,
  created_at TIMESTAMP WITH TIME ZONE
);

-- Invoices / Billing
CREATE TABLE IF NOT EXISTS invoices (
  id UUID PRIMARY KEY,
  customer_id UUID,
  amount BIGINT,
  paid BOOLEAN DEFAULT false,
  created_at TIMESTAMP WITH TIME ZONE
);