-- V2__seed_data.sql: Authentic seed data for SamosirVerse

-- 1. Default Admin (Password: admin123, BCrypt hashed)
INSERT INTO users (username, email, password, full_name, role)
VALUES (
    'admin',
    'admin@samosirverse.id',
    '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
    'Administrator SamosirVerse',
    'ROLE_ADMIN'
) ON CONFLICT (username) DO NOTHING;

-- 2. Authentic Destinations in Samosir
INSERT INTO destinations (name, slug, category, sub_district, description, short_description, cultural_significance, ticket_price, estimated_duration_hours, opening_hours, latitude, longitude, image_url, gallery_urls, facilities, is_featured, rating, review_count)
VALUES
(
    'Huta Siallagan & Batu Persidangan',
    'huta-siallagan-batu-persidangan',
    'BUDAYA_SEJARAH',
    'Simanindo',
    'Huta Siallagan adalah perkampungan kuno suku Batak Toba yang telah berdiri sejak ratusan tahun lalu di bawah kepemimpinan Raja Laga Siallagan. Dikelilingi tembok batu kokoh setinggi dua meter untuk pertahanan zaman perang antar-huta, kampung ini memuat situs sejarah paling tersohor di Samosir: Batu Parsidangan. Di bawah naungan pohon Hariara yang dikeramatkan, kursi dan meja batu purba masih berdiri kokoh sebagai saksi bisu musyawarah peradilan adat Batak untuk mengadili perkara pidana dan pelanggaran sumpah adat.',
    'Perkampungan kuno berbenteng batu dengan situs Batu Parsidangan tempat peradilan adat raja Batak tempo dulu.',
    'Simbol ketegasan penegakan hukum adat dan musyawarah mufakat (Runggun) masyarakat Batak Toba.',
    10000.00,
    2.0,
    '08:30 - 18:00 WIB',
    2.6841,
    98.8475,
    'https://images.unsplash.com/photo-1596422846543-75c6fc197f07?auto=format&fit=crop&w=1200&q=80',
    'https://images.unsplash.com/photo-1596422846543-75c6fc197f07?auto=format&fit=crop&w=800&q=80,https://images.unsplash.com/photo-1518548419970-58e3b4079ab2?auto=format&fit=crop&w=800&q=80',
    'Pemandu Budaya Resmi, Rumah Bolon Tradisional, Souvenir Ulos, Toilet Bersih, Area Parkir',
    TRUE,
    4.90,
    142
),
(
    'Makam Kuno Raja Sidabutar & Desa Wisata Tomok',
    'makam-raja-sidabutar-tomok',
    'BUDAYA_SEJARAH',
    'Simanindo',
    'Tomok merupakan pintu gerbang utama jalur budaya Samosir. Daya tarik utamanya adalah komplek Makam Kuno Raja-raja Sidabutar yang dipahat dari batu monolit utuh tanpa semen sejak abad ke-16. Di komplek ini pengunjung diwajibkan menyematkan kain ulos selendang sebagai tanda penghormatan. Tak jauh dari makam, pertunjukan boneka kayu mistis Sigale-gale siap menari diiringi tabuhan megah Gondang Sabangunan.',
    'Kompleks sarkofagus batu monolit berusia ratusan tahun dan pusat pertunjukan tari Sigale-Gale.',
    'Pusat spiritual dinasti Sidabutar yang pertama kali mendiami wilayah timur pesisir Samosir.',
    5000.00,
    2.0,
    '08:00 - 18:00 WIB',
    2.6586,
    98.8601,
    'https://images.unsplash.com/photo-1578632767115-351597cf2477?auto=format&fit=crop&w=1200&q=80',
    'https://images.unsplash.com/photo-1578632767115-351597cf2477?auto=format&fit=crop&w=800&q=80,https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=800&q=80',
    'Pertunjukan Sigale-gale, Kios Kerajinan Gorga, Gazebo Danau, Area Parkir Luas',
    TRUE,
    4.85,
    215
),
(
    'Bukit Holbung',
    'bukit-holbung',
    'ALAM',
    'Sitio-tio',
    'Dijuluki Bukit Teletubbies Danau Toba, Bukit Holbung menawarkan lanskap perbukitan savana hijau bertingkat yang menghadap langsung ke perairan biru Danau Toba yang tenang. Jalur setapak terawat mengantar wisatawan melintasi punggungan bukit menuju titik tertinggi dengan panorama 360 derajat tiada tanding. Tempat terbaik untuk menikmati ketenangan mentari terbit dan senja keemasan Samosir.',
    'Padang sabana hijau bergelombang dengan panorama spektakuler bentang perairan Danau Toba dari ketinggian.',
    'Tempat perenungan dan titik pandang keindahan bentang alam vulkanik Danau Toba.',
    10000.00,
    3.0,
    '06:00 - 18:30 WIB',
    2.5318,
    98.6729,
    'https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=1200&q=80',
    'https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=800&q=80',
    'Jalur Tracking Ramah Pemula, Spot Foto Panorama, Warung Kopi Lokal, Camping Ground',
    TRUE,
    4.95,
    310
),
(
    'Air Terjun Efrata',
    'air-terjun-efrata',
    'ALAM',
    'Harian',
    'Tersembunyi di balik perbukitan hijau Harian Boho, Air Terjun Efrata menyuguhkan hempasan air bertingkat setinggi kurang lebih 25 meter yang melebar bagaikan tirai perak alami. Airnya yang dingin berasal dari mata air pegunungan Bukit Barisan yang belum tercemar, dikelilingi hamparan sawah bertingkat dan tebing bebatuan purba yang memikat.',
    'Air terjun tirai setinggi 25 meter dengan suasana hutan asri dan persawahan hijau membentang.',
    'Sumber irigasi hulu yang menyuburkan persawahan tradisional masyarakat lembah Sosor Dolok.',
    7000.00,
    1.5,
    '07:30 - 17:30 WIB',
    2.5642,
    98.6658,
    'https://images.unsplash.com/photo-1432405972618-c60b0225b8f9?auto=format&fit=crop&w=1200&q=80',
    'https://images.unsplash.com/photo-1432405972618-c60b0225b8f9?auto=format&fit=crop&w=800&q=80',
    'Jalan Setapak Akses, Pondok Istirahat, Spot Foto Alami, Toilet',
    FALSE,
    4.80,
    98
),
(
    'Menara Pandang Tele',
    'menara-pandang-tele',
    'PETUALANGAN_RELIGI',
    'Harian',
    'Terletak di satu-satunya jalur darat yang menghubungkan Pulau Samosir dengan daratan utama Sumatera Utara, Menara Pandang Tele adalah gardu pandang ikonik 4 lantai. Dari menara ini, pandangan bebas membentang mengamati keindahan Danau Toba dari sudut pandang barat, lekukan Gunung Pusuk Buhit yang mistis, serta jembatan Tano Ponggol yang membelah selat.',
    'Menara observasi panorama tertinggi yang memperlihatkan lekukan magis pulau Samosir dan kaldera Danau Toba.',
    'Pintu gerbang darat bersejarah yang menyatukan Samosir dengan daratan utama Sumatera.',
    10000.00,
    1.5,
    '06:00 - 19:00 WIB',
    2.5519,
    98.6503,
    'https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?auto=format&fit=crop&w=1200&q=80',
    'https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?auto=format&fit=crop&w=800&q=80',
    'Gardu Pandang Bertingkat, Restoran Khas Kopi Samosir, Cinderamata, Musholla, Parkir',
    TRUE,
    4.82,
    188
),
(
    'Desa Tenun Ulos Lumban Suhi-Suhi',
    'desa-tenun-lumban-suhi-suhi',
    'KULINER_UMKM',
    'Pangururan',
    'Pusat pelestarian dan produksi kain tenun Ulos tradisional Batak paling masyhur di dunia. Hampir di setiap teras rumah panggung kayu tradisional di desa ini, terdengar irama khas alat tenun kayu gedogan yang dimainkan oleh tangan-tangan terampil para penenun wanita Batak yang mewarisi teknik turun-temurun hingga 4 generasi.',
    'Desa perajin kain Ulos otentik tempat menyaksikan proses penenunan benang alami di pelataran rumah adat.',
    'Jantung warisan wastra Batak Toba dan penjaga kearifan motif filosofis leluhur.',
    0.00,
    2.5,
    '08:00 - 17:00 WIB',
    2.6148,
    98.7180,
    'https://images.unsplash.com/photo-1607344645866-009c320c5ab8?auto=format&fit=crop&w=1200&q=80',
    'https://images.unsplash.com/photo-1607344645866-009c320c5ab8?auto=format&fit=crop&w=800&q=80',
    'Workshop Tenun Interaktif, Galeri Penjualan Langsung Kreator, Rumah Adat Bolon Terawat',
    TRUE,
    4.92,
    167
),
(
    'Danau Sidihoni (Danau di Atas Danau)',
    'danau-sidihoni',
    'ALAM',
    'Pangururan',
    'Fenomena geologis menakjubkan di dataran tinggi pedalaman Samosir: sebuah danau air tawar alami yang berada persis di atas pulau yang berada di tengah danau vulkanik raksasa. Airnya yang tenang dikelilingi perbukitan pinus dan padang rumput tempat ternak kerbau warga digembalakan secara damai.',
    'Keajaiban alam langka sebuah danau alami yang bertengger di dataran tinggi pulau di tengah Danau Toba.',
    'Dihormati sebagai sumber air suci pedalaman Samosir dan penanda harmoni ekosistem hulu.',
    5000.00,
    1.5,
    '08:00 - 18:00 WIB',
    2.5702,
    98.7456,
    'https://images.unsplash.com/photo-1470071459604-3b5ec3a7fe05?auto=format&fit=crop&w=1200&q=80',
    'https://images.unsplash.com/photo-1470071459604-3b5ec3a7fe05?auto=format&fit=crop&w=800&q=80',
    'Spot Foto Alami, Danau Air Tawar, Gazebo Santai, Pemandangan Padang Rumput',
    FALSE,
    4.76,
    84
),
(
    'Gunung Pusuk Buhit',
    'gunung-pusuk-buhit',
    'PETUALANGAN_RELIGI',
    'Pangururan',
    'Puncak sakral berketinggian 1.972 mdpl yang diyakini dalam mitologi Batak sebagai titik pertama turunnya manusia Batak pertama (Siraja Batak) ke bumi dari kayangan. Jalur pendakian menyajikan keindahan sabana, kawah belerang, mata air sakral, serta pemandangan kaldera Toba yang luar biasa megah.',
    'Puncak sakral asal mula suku Batak dengan panorama kaldera megah dan jalur pendakian eksotis.',
    'Pusat spiritual dan kosmologi tertinggi etnis Batak di seluruh dunia.',
    15000.00,
    5.0,
    'Buka 24 Jam (Registrasi Pos 06:00 - 17:00)',
    2.6075,
    98.6542,
    'https://images.unsplash.com/photo-1519681393784-d120267933ba?auto=format&fit=crop&w=1200&q=80',
    'https://images.unsplash.com/photo-1519681393784-d120267933ba?auto=format&fit=crop&w=800&q=80',
    'Pos Pemandu Adat, Jalur Pendakian Terkelola, Area Kemah Bukit, Pos Registrasi',
    TRUE,
    4.91,
    124
);

-- 3. Authentic Creators & Local Artisans in Samosir
INSERT INTO creators (name, slug, craft_type, village, bio, whatsapp_number, is_whatsapp_verified, profile_image_url, years_of_experience, awards)
VALUES
(
    'Nai Sintong Sinaga',
    'nai-sintong-sinaga',
    'Penenun Ulos Tradisional Pewarna Alam',
    'Desa Lumban Suhi-Suhi, Simanindo',
    'Maestro tenun yang telah mendedikasikan hidupnya selama lebih dari 30 tahun melestarikan Ulos klasik Batak Toba. Beliau memelopori kembalinya penggunaan pewarna alami dari daun salaon, kulit kayu mahoni, dan kunyit hutan demi menjaga kelestarian lingkungan dan keaslian filosofi wastra.',
    '+6281260819451',
    TRUE,
    'https://images.unsplash.com/photo-1544005313-94ddf0286df2?auto=format&fit=crop&w=600&q=80',
    32,
    'Penerima Penghargaan Anugerah Desa Wisata Tenun Sumut 2023, Pembina Komunitas Tenun Samosir'
),
(
    'Amang Poltak Malau',
    'amang-poltak-malau',
    'Pengrajin Seni Ukir Gorga Batak',
    'Desa Tomok, Simanindo',
    'Pengrajin kayu generasi ketiga di Tomok yang setia mengukir ragam hias Gorga Batak seperti Singa-singa, Ipon-ipon, dan Paung pada kayu ingul dan jior. Karyanya telah menghiasi berbagai museum etnik dan rumah adat di Indonesia dan mancanegara.',
    '+6281375218902',
    TRUE,
    'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?auto=format&fit=crop&w=600&q=80',
    27,
    'Sertifikasi Pelestari Seni Tradisi Kayu Batak Kemenparekraf 2022'
),
(
    'Martua Simbolon',
    'martua-simbolon',
    'Petani & Roaster Kopi Arabica Samosir',
    'Kelurahan Siogung-Ogung, Pangururan',
    'Pegiat kopi muda yang membina petani lokal di lereng Pusuk Buhit untuk membudidayakan varietas Arabica Sigarar Utang dan Lasuna pada ketinggian 1.400 mdpl dengan proses wet hulling tradisional Batak yang menghasilkan citarasa rempah rempah khas.',
    '+6282164392011',
    TRUE,
    'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?auto=format&fit=crop&w=600&q=80',
    11,
    'Juara 2 Cupping Specialty Coffee Toba Caldera Fest 2023'
),
(
    'Butet Nainggolan',
    'butet-nainggolan',
    'Pengolah Kuliner Rempah Andaliman & Hasil Toba',
    'Tuk-Tuk Siadong, Simanindo',
    'Inovator UMKM kuliner yang mengangkat andaliman (merica batak hutan) menjadi produk olahan sambal segar higienis dan bumbu rendang andaliman kemasan ramah wisatawan.',
    '+6285270183492',
    TRUE,
    'https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?auto=format&fit=crop&w=600&q=80',
    8,
    'Pemenang UMKM Kuliner Inovatif Samosir Creative Hub 2024'
);

-- 4. Authentic UMKM Products
INSERT INTO products (creator_id, name, slug, category, price, description, image_url, stock_status, is_authentic_handcrafted)
VALUES
(
    1,
    'Ulos Ragi Hotang Pewarna Alami Eksklusif',
    'ulos-ragi-hotang-pewarna-alami',
    'TENUN_ULOS',
    850000.00,
    'Ulos Ragi Hotang ditenun menggunakan benang katun halus dengan pewarna organik daun salaon (indigo liar Samosir). Memiliki makna keteguhan hati dan biasa dihadiahkan dalam upacara pernikahan adat Batak.',
    'https://images.unsplash.com/photo-1607344645866-009c320c5ab8?auto=format&fit=crop&w=800&q=80',
    'TERSEDIA',
    TRUE
),
(
    1,
    'Ulos Sadum Tenun Tangan Motif Moderen',
    'ulos-sadum-tenun-tangan',
    'TENUN_ULOS',
    550000.00,
    'Kain selendang ulos sadum dengan kombinasi benang emas dan merah marun yang anggun, cocok sebagai syal pesta atau ornamen busana modern bernuansa etnik.',
    'https://images.unsplash.com/photo-1617627143750-d86bc21e42bb?auto=format&fit=crop&w=800&q=80',
    'TERSEDIA',
    TRUE
),
(
    2,
    'Plakat Ukir Dinding Gorga Singa-Singa Kayu Ingul',
    'plakat-gorga-singa-singa',
    'KERAJINAN_GORGA',
    380000.00,
    'Ukiran kayu khas Batak bermotif Singa-singa yang melambangkan keadilan, kepemimpinan, dan perlindungan rumah. Dipahat manual dari kayu ingul bertekstur kuat.',
    'https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?auto=format&fit=crop&w=800&q=80',
    'TERSEDIA',
    TRUE
),
(
    3,
    'Kopi Arabica Samosir Pusuk Buhit Single Origin (250g)',
    'kopi-arabica-samosir-pusuk-buhit',
    'KOPI_REMPAH',
    75000.00,
    'Biji kopi Arabica dipetik merah dari lereng Pusuk Buhit di ketinggian 1.450 mdpl. Profil sangrai medium roasted dengan notes rasa rempah herbal, cokelat, dan aroma jeruk andaliman yang lembut.',
    'https://images.unsplash.com/photo-1587734195503-904fca47e0e9?auto=format&fit=crop&w=800&q=80',
    'TERSEDIA',
    TRUE
),
(
    4,
    'Sambal Andaliman Khas Toba Kemasan Kaca (180g)',
    'sambal-andaliman-khas-toba',
    'KULINER_KHAS',
    45000.00,
    'Sambal cabe rawit segar berpadu dengan andaliman asli pegunungan Samosir yang menghadirkan sensasi getir gurih yang unik dan khas lidah Batak.',
    'https://images.unsplash.com/photo-1588166524941-3bf61a9c41db?auto=format&fit=crop&w=800&q=80',
    'TERSEDIA',
    TRUE
);

-- 5. Batak Toba Cultural Stories
INSERT INTO cultural_stories (title, slug, category, summary, content, batak_quote, quote_meaning, reading_time_minutes, cover_image_url, related_destination_id)
VALUES
(
    'Dalihan Na Tolu: Tri Tunggal Falsafah Harmoni Batak Toba',
    'dalihan-na-tolu-falsafah-hidup-batak',
    'FILOSOFI',
    'Fondasi struktur sosial dan etika bermasyarakat suku Batak yang menjaga keseimbangan kekeluargaan dan penghormatan antar-marga.',
    'Dalihan Na Tolu secara harfiah bermakna "tungku berkaki tiga", perlambang bahwa suatu perapian tidak akan pernah tegak kokoh tanpa ketiga tumpuannya. Dalam kebudayaan Batak Toba, falsafah ini mengatur relasi tiga kelompok kekerabatan: Somba Marhula-hula (hormat kepada pihak keluarga pemberi istri), Manat Mardongan Tubu (bersikap bijak dan hati-hati kepada kerabat semarga), serta Elek Marboru (lemah lembut dan mengayomi pihak penerima istri). Ketiganya menciptakan tatanan sosial yang egaliter sekaligus penuh rasa hormat.',
    'Somba marhula-hula, manat mardongan tubu, elek marboru.',
    'Hormat kepada keluarga pihak istri, bijaksana kepada teman semarga, dan mengayomi anak perempuan / saudara perempuan.',
    6,
    'https://images.unsplash.com/photo-1518548419970-58e3b4079ab2?auto=format&fit=crop&w=1200&q=80',
    1
),
(
    'Misteri Tari Boneka Sigale-Gale: Dari Tangisan Ayah Menjadi Seni Agung',
    'misteri-tari-boneka-sigale-gale',
    'LEGENDA',
    'Kisah keharuan Raja Rahat yang mengilhami pemahatan boneka kayu yang mampu menari untuk menghibur duka mendalam sang ayah.',
    'Legenda mengisahkan bahwa Raja Rahat di tanah Samosir memiliki putra tunggal yang gagah berani bernama Datu Pejel. Ketika putranya gugur di medan peperangan mempertahankan tanah air, sang raja jatuh sakit parah akibat kesedihan yang tak tertahankan. Para penasihat kerajaan memanggil Datu (ahli spiritual dan pemahat) untuk membuat patung kayu menyerupai sang pangeran. Melalui lantunan Gondang Sabangunan dan mantra pemanggilan arwah, patung yang diberi nama Sigale-gale ini dapat menari di halaman istana, mengobati kerinduan sang raja.',
    'Molo adong pe sihabiaran, anggo roha marsada pos do roha.',
    'Bila ada duka dan kekhawatiran, hati yang bersatu dan tulus akan mendatangkan ketenteraman.',
    7,
    'https://images.unsplash.com/photo-1578632767115-351597cf2477?auto=format&fit=crop&w=1200&q=80',
    2
),
(
    'Filosofi Ulos: Kehangatan Jiwa dan Doa Sepanjang Hayat',
    'filosofi-ulos-kehangatan-jiwa',
    'TRADISI',
    'Bagi masyarakat Batak, Ulos bukan sekadar helai benang tenun, melainkan wujud perlambang kasih sayang, perlindungan, dan doa restu para leluhur.',
    'Nenek moyang orang Batak meyakini tiga sumber kehangatan hidup: api, matahari, dan ulos. Di dataran tinggi Samosir yang berhawa dingin, ulos menjadi pembungkus raga dan jiwa. Ulos diberikan pada momen-momen penting dalam siklus kehidupan manusia: mulai dari tujuh bulanan kehamilan (Ulos Tondi), pernikahan (Ulos Hela), hingga wafat. Setiap motif, warna, dan pintalan memiliki tata krama pemberian (Mangulosi) yang tidak boleh tertukar.',
    'Ijuk pangihot ni dame, ulos mangalehon las ni roha.',
    'Sebagaimana ijuk mempererat tali pengikat, kain ulos menganugerahkan kehangatan dan kedamaian hati.',
    5,
    'https://images.unsplash.com/photo-1607344645866-009c320c5ab8?auto=format&fit=crop&w=1200&q=80',
    6
);

-- 6. Local Experiences and Agenda
INSERT INTO experiences (title, slug, type, location, event_date, duration, price, description, organizer_name, whatsapp_booking, image_url, max_participants)
VALUES
(
    'Workshop Menenun Ulos Pewarna Alami 1 Hari',
    'workshop-tenun-ulos-1-hari',
    'WORKSHOP',
    'Desa Lumban Suhi-Suhi, Samosir',
    'Setiap Hari Selasa & Sabtu (09:00 WIB)',
    '5 Jam',
    175000.00,
    'Belajar langsung dari maestro penenun Lumban Suhi-Suhi tentang pengenalan motif Batak, pencampuran pewarna indigo dan kulit kayu, hingga mempraktikkan alat tenun gedogan tradisional di teras rumah bolon.',
    'Komunitas Tenun Lumban Suhi-Suhi',
    '+6281260819451',
    'https://images.unsplash.com/photo-1607344645866-009c320c5ab8?auto=format&fit=crop&w=1200&q=80',
    10
),
(
    'Trekking Budaya & Meditasi Fajar Pusuk Buhit',
    'trekking-budaya-pusuk-buhit',
    'TUR_BUDAYA',
    'Sianjur Mula-Mula, Pangururan',
    'Setiap Akhir Pekan (Jumat - Sabtu)',
    '1 Hari (Menginap di Basecamp)',
    250000.00,
    'Menapaki jalur legenda leluhur Batak menuju puncak Pusuk Buhit diiringi kisah mitologi dari pemandu adat, menyaksikan matahari terbit di atas Danau Toba, dan singgah di Aek Sipitu Dai.',
    'Pemandu Budaya Pusuk Buhit Samosir',
    '+6282164392011',
    'https://images.unsplash.com/photo-1519681393784-d120267933ba?auto=format&fit=crop&w=1200&q=80',
    15
),
(
    'Samosir Music International & Toba Gondang Fest 2026',
    'samosir-music-international-2026',
    'FESTIVAL_AGENDA',
    'Open Air Stage Tuk-Tuk Siadong',
    '22 - 24 Agustus 2026',
    '3 Hari Acara Puncak',
    0.00,
    'Festival musik internasional ikonik yang memadukan musisi mancanegara dengan orkestra Gondang Sabangunan dan lagu-lagu legendaris Batak di panggung terbuka berlatar deburan Danau Toba.',
    'Dinas Pariwisata Samosir & Project Toba',
    '+6281375218902',
    'https://images.unsplash.com/photo-1465847899084-d164df4dedc6?auto=format&fit=crop&w=1200&q=80',
    5000
);
