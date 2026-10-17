-- V1__init_schema.sql: Initial schema for SamosirVerse

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'ROLE_ADMIN',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS destinations (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    slug VARCHAR(150) NOT NULL UNIQUE,
    category VARCHAR(50) NOT NULL,
    sub_district VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    short_description VARCHAR(255) NOT NULL,
    cultural_significance TEXT,
    ticket_price DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    estimated_duration_hours DECIMAL(3, 1) NOT NULL DEFAULT 1.5,
    opening_hours VARCHAR(100) NOT NULL DEFAULT '08:00 - 18:00 WIB',
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    image_url VARCHAR(500) NOT NULL,
    gallery_urls TEXT,
    facilities TEXT,
    is_featured BOOLEAN NOT NULL DEFAULT FALSE,
    rating DECIMAL(3, 2) NOT NULL DEFAULT 4.80,
    review_count INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS creators (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,
    craft_type VARCHAR(100) NOT NULL,
    village VARCHAR(100) NOT NULL,
    bio TEXT NOT NULL,
    whatsapp_number VARCHAR(30) NOT NULL,
    is_whatsapp_verified BOOLEAN NOT NULL DEFAULT TRUE,
    profile_image_url VARCHAR(500) NOT NULL,
    years_of_experience INT NOT NULL DEFAULT 5,
    awards TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    creator_id BIGINT NOT NULL REFERENCES creators(id) ON DELETE CASCADE,
    name VARCHAR(150) NOT NULL,
    slug VARCHAR(150) NOT NULL UNIQUE,
    category VARCHAR(50) NOT NULL,
    price DECIMAL(12, 2) NOT NULL,
    description TEXT NOT NULL,
    image_url VARCHAR(500) NOT NULL,
    stock_status VARCHAR(30) NOT NULL DEFAULT 'TERSEDIA',
    is_authentic_handcrafted BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cultural_stories (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    slug VARCHAR(200) NOT NULL UNIQUE,
    category VARCHAR(50) NOT NULL,
    summary VARCHAR(300) NOT NULL,
    content TEXT NOT NULL,
    batak_quote VARCHAR(300),
    quote_meaning VARCHAR(300),
    reading_time_minutes INT NOT NULL DEFAULT 5,
    cover_image_url VARCHAR(500) NOT NULL,
    related_destination_id BIGINT REFERENCES destinations(id) ON DELETE SET NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS experiences (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    slug VARCHAR(200) NOT NULL UNIQUE,
    type VARCHAR(50) NOT NULL,
    location VARCHAR(150) NOT NULL,
    event_date VARCHAR(100) NOT NULL,
    duration VARCHAR(50) NOT NULL,
    price DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    description TEXT NOT NULL,
    organizer_name VARCHAR(100) NOT NULL,
    whatsapp_booking VARCHAR(30) NOT NULL,
    image_url VARCHAR(500) NOT NULL,
    max_participants INT NOT NULL DEFAULT 20,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for performance
CREATE INDEX IF NOT EXISTS idx_destinations_category ON destinations(category);
CREATE INDEX IF NOT EXISTS idx_destinations_sub_district ON destinations(sub_district);
CREATE INDEX IF NOT EXISTS idx_destinations_featured ON destinations(is_featured);
CREATE INDEX IF NOT EXISTS idx_creators_village ON creators(village);
CREATE INDEX IF NOT EXISTS idx_products_creator ON products(creator_id);
CREATE INDEX IF NOT EXISTS idx_cultural_stories_category ON cultural_stories(category);
CREATE INDEX IF NOT EXISTS idx_experiences_type ON experiences(type);
