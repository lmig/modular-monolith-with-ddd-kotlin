-- Orders table
CREATE TABLE IF NOT EXISTS orders (
  id UUID PRIMARY KEY,
  status VARCHAR(50),
  total BIGINT,
  created_at TIMESTAMP WITH TIME ZONE
);

-- Event store
CREATE TABLE IF NOT EXISTS event_store (
  id BIGSERIAL PRIMARY KEY,
  aggregate_id UUID NOT NULL,
  type VARCHAR(200),
  payload TEXT,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Outbox (destination indicates target context or logical destination)
CREATE TABLE IF NOT EXISTS outbox (
  id BIGSERIAL PRIMARY KEY,
  aggregate_id UUID NOT NULL,
  type VARCHAR(200),
  payload TEXT,
  destination VARCHAR(200),
  dispatched BOOLEAN DEFAULT false,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Inbox (target contexts read from this table)
CREATE TABLE IF NOT EXISTS inbox (
  id BIGSERIAL PRIMARY KEY,
  origin_aggregate_id UUID,
  type VARCHAR(200),
  payload TEXT,
  processed BOOLEAN DEFAULT false,
  processed_at TIMESTAMP WITH TIME ZONE,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);