# 🌿 SamosirVerse — Creative Hub for App Talent Samosir 2026

Platform Ekosistem Pariwisata Cerdas, Warisan Budaya Batak Toba, dan Direktori Kreator Lokal Pulau Samosir.

---

## 📌 Ringkasan Proyek
**SamosirVerse** dibangun sebagai karya inovasi teknologi digital untuk kompetisi **Creative Hub for App Talent Samosir 2026**. Aplikasi ini dirancang untuk menjawab tantangan integrasi pariwisata, pelestarian warisan budaya takbenda, serta pemberdayaan ekonomi perajin lokal (penenun ulos, pengukir gorga, petani kopi Pusuk Buhit, dan UMKM kuliner andaliman) di seluruh wilayah Kabupaten Samosir.

### 🎨 Desain: Modern Tropical Editorial
Antarmuka dibangun dengan palet warna khas alam vulkanik dan kebudayaan Pulau Samosir:
- **Forest Green** (`#123C36`): Keasrian vegetasi perbukitan Bukit Barisan dan Danau Toba.
- **Warm Ivory** (`#F6F1E7`): Nuansa kanvas editorial yang elegan dan ramah pembaca.
- **Terracotta** (`#C96E48`): Kehangatan tanah liat, bara tungku, dan aksen wastra Batak.
- **Lake Mist** (`#8AB9C2`): Kabut perairan Danau Toba di fajar hari.

---

## 🚀 Arsitektur & Teknologi

| Lapisan | Teknologi | Deskripsi |
| :--- | :--- | :--- |
| **Frontend** | SvelteKit 2 + TypeScript + Vite | Performa tinggi, transisi reaktif, dan antarmuka responsif tanpa lag |
| **Peta Interaktif** | Leaflet | Penanda geospasial presisi untuk seluruh destinasi dan sanggar perajin |
| **Backend** | Java 21 LTS + Spring Boot 3.3.4 | RESTful API kuat, aman, arsitektur modular, dan performa tingkat enterprise |
| **Keamanan** | Spring Security 6 + JWT + BCrypt | Otentikasi stateless dengan password hashing BCrypt dan proteksi role admin |
| **Basis Data** | PostgreSQL 16 + Flyway | Skema relasional yang terstruktur dengan migrasi otomatis `V1` dan `V2` |
| **Pengujian** | JUnit 5 + MockMvc + Svelte Check | 100% test coverage untuk endpoint inti, trip planner engine, dan validasi tipe |
| **Orkestrasi** | Docker & Docker Compose | Kemudahan instalasi dan deployment instan database PostgreSQL dan pgAdmin 4 |

---

## ✨ Fitur-Fitur Utama (MVP SamosirVerse)

1. **Landing Page Editorial:**
   - Hero banner tipografis dengan statistik pulau.
   - Widget simulasi cepat Smart Trip Planner.
   - Sorotan destinasi unggulan, cerita budaya, direktori maestro, dan kalender kegiatan.
2. **Katalog Destinasi & Halaman Detail:**
   - Pencarian cerdas dan penyaringan berdasarkan kategori wisata serta 6 kecamatan di Samosir (Simanindo, Pangururan, Harian, Sitio-tio, Onan Runggu, Palipi).
   - Halaman detail yang memuat galeri foto, fasilitas, jam buka, tiket masuk, koordinat GPS, serta makna filosofis budaya Batak.
3. **Peta Interaktif Geopark Samosir:**
   - Peta interaktif Leaflet dengan pin kustom bertema Batak dan popup kartu informatif.
4. **Rule-Based Smart Trip Planner:**
   - Algoritma penyusunan rute berbasis kedekatan geografis di Pulau Samosir (1 Hari, 2 Hari 1 Malam, atau 3 Hari 2 Malam).
   - Pemilihan gaya perjalanan (Budaya, Alam, Santai, Kriya) dan kategori anggaran (Ekonomis, Standar, Premium).
   - Rincian estimasi biaya transparan dan panduan etika budaya Batak Toba (salam Horas, tata krama berziarah, etika memotret penenun).
5. **Katalog Cerita Budaya Batak Toba:**
   - Artikel naratif berkualitas mengenai filosofi Dalihan Na Tolu, legenda boneka Sigale-gale, dan makna sakral kain ulos.
   - Panggilan kutipan peribahasa Batak otentik beserta terjemahannya.
6. **Direktori Kreator & Produk UMKM:**
   - Profil para maestro lokal lengkap dengan tahun pengalaman dan penghargaan.
   - **Validasi Kontak WhatsApp Resmi:** Tombol WhatsApp langsung dengan pesan otomatis tanpa perantara.
   - Etalase produk cinderamata buatan tangan dengan label keaslian 100%.
7. **Kalender Pengalaman & Agenda Festival:**
   - Pendaftaran workshop menenun, tur budaya Pusuk Buhit, dan festival musik internasional.
8. **Portal & Dashboard Administrator:**
   - Login terproteksi token JWT.
   - Panel ringkasan metrik statistik real-time.
   - Manajemen CRUD lengkap (Tambah, Edit, Hapus dengan konfirmasi) untuk Destinasi, Kreator, Produk, Cerita, dan Agenda.

---

## 🛠️ Petunjuk Menjalankan Aplikasi

### 1. Prasyarat Lingkungan
- **Node.js:** v18+ (Disarankan v20 atau v24)
- **Java:** JDK 21 LTS (Microsoft OpenJDK 21 terpasang)
- **Maven:** Apache Maven 3.9+
- **Database:** PostgreSQL 16 (atau menggunakan Docker Compose yang disertakan)

### 2. Menjalankan Database PostgreSQL

#### Cara A: Menggunakan Docker Compose (Direkomendasikan)
```bash
docker compose up -d
```
Layanan PostgreSQL akan berjalan pada `localhost:5432` dengan database `samosirverse`, user `postgres`, dan password `postgrespassword`. pgAdmin 4 dapat diakses pada `http://localhost:5050`.

#### Cara B: PostgreSQL Lokal Manual
Buat database bernama `samosirverse` di PostgreSQL lokal Anda, lalu sesuaikan konfigurasi di file `.env` atau `backend/src/main/resources/application.yml`.

### 3. Menjalankan Backend (Spring Boot 3 + Java 21)
Masuk ke direktori `backend/`:
```bash
cd backend

# Menjalankan pengujian JUnit 5 & build JAR
mvn clean package

# Menjalankan server Spring Boot
mvn spring-boot:run
```
> **Catatan Pengembangan Lokal:** Jika PostgreSQL belum dijalankan di host, Anda dapat menjalankan backend dengan profil lokal mandiri:
> `mvn spring-boot:run -Dspring-boot.run.profiles=local`
Backend akan aktif pada: **`http://localhost:8080`**

### 4. Menjalankan Frontend (SvelteKit + TypeScript)
Buka terminal baru dan masuk ke direktori `frontend/`:
```bash
cd frontend

# Pasang dependensi (jika belum)
npm install

# Jalankan server pengembangan
npm run dev
```
Frontend akan aktif pada: **`http://localhost:5173`**

---

## 🔐 Kredensial Pengujian & Administrator

Untuk kemudahan penilaian juri dan demonstrasi, akun administrator telah disiapkan secara default:
- **URL Login:** `http://localhost:5173/admin/login`
- **Username:** `admin`
- **Password:** `admin123`
- *Tersedia tombol "Isi Otomatis" di halaman login untuk demonstrasi instan.*

---

## 📋 Dokumentasi REST API

### 1. Endpoint Publik
- `GET /api/destinations` — Daftar destinasi (filter: `category`, `subDistrict`, `search`)
- `GET /api/destinations/featured` — Destinasi unggulan
- `GET /api/destinations/slug/{slug}` — Detail destinasi berdasarkan slug
- `GET /api/creators` — Direktori kreator (filter: `craftType`, `search`)
- `GET /api/creators/slug/{slug}` — Profil kreator
- `GET /api/products` — Katalog produk (filter: `category`, `creatorId`, `search`)
- `GET /api/products/slug/{slug}` — Detail produk
- `GET /api/cultural-stories` — Katalog cerita budaya
- `GET /api/cultural-stories/slug/{slug}` — Artikel budaya lengkap
- `GET /api/experiences` — Kalender pengalaman & agenda
- `POST /api/trip-planner/generate` — Penyusunan itinerary cerdas berbasis aturan
- `POST /api/auth/login` — Autentikasi dan penerbitan token JWT

### 2. Endpoint Administrator (Wajib Header: `Authorization: Bearer <token>`)
- `GET /api/admin/stats` — Metrik ringkasan dashboard
- `POST /api/admin/destinations` — Tambah destinasi baru
- `PUT /api/admin/destinations/{id}` — Perbarui destinasi
- `DELETE /api/admin/destinations/{id}` — Hapus destinasi
- `POST /api/admin/creators` — Tambah profil perajin
- `PUT /api/admin/creators/{id}` — Perbarui profil perajin
- `DELETE /api/admin/creators/{id}` — Hapus profil perajin
- `POST /api/admin/products` — Tambah produk UMKM
- `PUT /api/admin/products/{id}` — Perbarui produk UMKM
- `DELETE /api/admin/products/{id}` — Hapus produk UMKM
- `POST /api/admin/cultural-stories` — Tambah cerita budaya
- `PUT /api/admin/cultural-stories/{id}` — Perbarui cerita budaya
- `DELETE /api/admin/cultural-stories/{id}` — Hapus cerita budaya
- `POST /api/admin/experiences` — Tambah agenda
- `PUT /api/admin/experiences/{id}` — Perbarui agenda
- `DELETE /api/admin/experiences/{id}` — Hapus agenda

---

## 🏆 Skenario Demonstrasi Kompetisi

Gunakan skenario berikut saat mempresentasikan SamosirVerse di hadapan dewan juri:

1. **Eksplorasi Beranda & Nilai Budaya (2 Menit):**
   - Buka `http://localhost:5173/`.
   - Tunjukkan estetika *Modern Tropical Editorial* yang menghadirkan identitas Pulau Samosir secara elegan dan profesional.
   - Perlihatkan falsafah *Dalihan Na Tolu* dan pesan budaya yang menjadi fondasi platform.
2. **Simulasi Smart Trip Planner (2 Menit):**
   - Pada widget trip planner atau halaman `/trip-planner`, pilih: Durasi **2 Hari 1 Malam**, Tema **Budaya & Sejarah**, Anggaran **Standar**.
   - Klik **Generate Itinerary Cerdas**.
   - Tunjukkan bagaimana sistem menyusun rute logis hari ke-1 (pesisir timur Simanindo, Makam Sidabutar, Huta Siallagan, Lumban Suhi-suhi) dan hari ke-2 (geopark Tele, Efrata, Bukit Holbung) lengkap dengan rincian biaya tiket dan etika budaya Batak.
3. **Peta Interaktif Geopark Samosir (1 Menit):**
   - Buka halaman `/peta`.
   - Klik penanda pin untuk melihat kartu popup foto destinasi, tarif tiket, dan tautan rute.
4. **Pemberdayaan Kreator Lokal & Integrasi WhatsApp (2 Menit):**
   - Buka halaman `/kreator` dan pilih profil **Nai Sintong Sinaga** (Penenun Ulos Pewarna Alam).
   - Klik tombol **Hubungi Langsung via WhatsApp** atau tombol pesan produk pada kain tenun ulos.
   - Tunjukkan bahwa sistem membuka WhatsApp dengan format pesan pemesanan yang sudah terisi otomatis tanpa potongan komisi.
5. **Portal Admin & CRUD Data Real-Time (3 Menit):**
   - Buka `/admin/login`, gunakan tombol "Isi Otomatis" untuk masuk.
   - Di dashboard, tunjukkan 5 kartu statistik metrik.
   - Tambahkan destinasi baru atau produk baru melalui tombol "➕ Tambah Data Baru".
   - Tunjukkan bahwa data yang baru ditambahkan langsung muncul pada tabel dan antarmuka publik, serta fungsi edit dan hapus bekerja dengan konfirmasi aman dan notifikasi toast.

---

## 👥 Pengembang
Dikembangkan dengan dedikasi penuh untuk **Creative Hub for App Talent Samosir 2026**.
- **Repositori:** [https://github.com/Aditya-Ifs03/SamosirVerse1.git](https://github.com/Aditya-Ifs03/SamosirVerse1.git)
- **Lisensi:** MIT License
