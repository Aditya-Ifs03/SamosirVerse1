import type {
  Destination,
  Creator,
  Product,
  CulturalStory,
  Experience,
  TripPlanRequest,
  TripPlanResponse,
  AuthResponse,
  DashboardStats,
  ApiResponse
} from './types';

const API_BASE = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api';

function getAuthHeader(): Record<string, string> {
  if (typeof window !== 'undefined') {
    const token = localStorage.getItem('samosirverse_token');
    if (token) {
      return { Authorization: `Bearer ${token}` };
    }
  }
  return {};
}

// Fallback Authentic Data in case backend is offline or starting up
export const FALLBACK_DESTINATIONS: Destination[] = [
  {
    id: 1,
    name: 'Huta Siallagan & Batu Persidangan',
    slug: 'huta-siallagan-batu-persidangan',
    category: 'BUDAYA_SEJARAH',
    subDistrict: 'Simanindo',
    description: 'Huta Siallagan adalah perkampungan kuno suku Batak Toba yang telah berdiri sejak ratusan tahun lalu di bawah kepemimpinan Raja Laga Siallagan. Dikelilingi tembok batu kokoh setinggi dua meter untuk pertahanan zaman perang antar-huta, kampung ini memuat situs sejarah paling tersohor di Samosir: Batu Parsidangan. Di bawah naungan pohon Hariara yang dikeramatkan, kursi dan meja batu purba masih berdiri kokoh sebagai saksi bisu musyawarah peradilan adat Batak untuk mengadili perkara pidana dan pelanggaran sumpah adat.',
    shortDescription: 'Perkampungan kuno berbenteng batu dengan situs Batu Parsidangan tempat peradilan adat raja Batak tempo dulu.',
    culturalSignificance: 'Simbol ketegasan penegakan hukum adat dan musyawarah mufakat (Runggun) masyarakat Batak Toba.',
    ticketPrice: 10000,
    estimatedDurationHours: 2.0,
    openingHours: '08:30 - 18:00 WIB',
    latitude: 2.6841,
    longitude: 98.8475,
    imageUrl: 'https://images.unsplash.com/photo-1596422846543-75c6fc197f07?auto=format&fit=crop&w=1200&q=80',
    galleryUrls: 'https://images.unsplash.com/photo-1596422846543-75c6fc197f07?auto=format&fit=crop&w=800&q=80,https://images.unsplash.com/photo-1518548419970-58e3b4079ab2?auto=format&fit=crop&w=800&q=80',
    facilities: 'Pemandu Budaya Resmi, Rumah Bolon Tradisional, Souvenir Ulos, Toilet Bersih, Area Parkir',
    isFeatured: true,
    rating: 4.9,
    reviewCount: 142
  },
  {
    id: 2,
    name: 'Makam Kuno Raja Sidabutar & Desa Wisata Tomok',
    slug: 'makam-raja-sidabutar-tomok',
    category: 'BUDAYA_SEJARAH',
    subDistrict: 'Simanindo',
    description: 'Tomok merupakan pintu gerbang utama jalur budaya Samosir. Daya tarik utamanya adalah komplek Makam Kuno Raja-raja Sidabutar yang dipahat dari batu monolit utuh tanpa semen sejak abad ke-16. Di komplek ini pengunjung diwajibkan menyematkan kain ulos selendang sebagai tanda penghormatan. Tak jauh dari makam, pertunjukan boneka kayu mistis Sigale-gale siap menari diiringi tabuhan megah Gondang Sabangunan.',
    shortDescription: 'Kompleks sarkofagus batu monolit berusia ratusan tahun dan pusat pertunjukan tari Sigale-Gale.',
    culturalSignificance: 'Pusat spiritual dinasti Sidabutar yang pertama kali mendiami wilayah timur pesisir Samosir.',
    ticketPrice: 5000,
    estimatedDurationHours: 2.0,
    openingHours: '08:00 - 18:00 WIB',
    latitude: 2.6586,
    longitude: 98.8601,
    imageUrl: 'https://images.unsplash.com/photo-1578632767115-351597cf2477?auto=format&fit=crop&w=1200&q=80',
    facilities: 'Pertunjukan Sigale-gale, Kios Kerajinan Gorga, Gazebo Danau, Area Parkir Luas',
    isFeatured: true,
    rating: 4.85,
    reviewCount: 215
  },
  {
    id: 3,
    name: 'Bukit Holbung',
    slug: 'bukit-holbung',
    category: 'ALAM',
    subDistrict: 'Sitio-tio',
    description: 'Dijuluki Bukit Teletubbies Danau Toba, Bukit Holbung menawarkan lanskap perbukitan savana hijau bertingkat yang menghadap langsung ke perairan biru Danau Toba yang tenang. Jalur setapak terawat mengantar wisatawan melintasi punggungan bukit menuju titik tertinggi dengan panorama 360 derajat tiada tanding.',
    shortDescription: 'Padang sabana hijau bergelombang dengan panorama spektakuler bentang perairan Danau Toba dari ketinggian.',
    culturalSignificance: 'Titik pandang keindahan bentang alam vulkanik Danau Toba dan ruang perenungan alam.',
    ticketPrice: 10000,
    estimatedDurationHours: 3.0,
    openingHours: '06:00 - 18:30 WIB',
    latitude: 2.5318,
    longitude: 98.6729,
    imageUrl: 'https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=1200&q=80',
    facilities: 'Jalur Tracking Ramah Pemula, Spot Foto Panorama, Warung Kopi Lokal, Camping Ground',
    isFeatured: true,
    rating: 4.95,
    reviewCount: 310
  },
  {
    id: 4,
    name: 'Air Terjun Efrata',
    slug: 'air-terjun-efrata',
    category: 'ALAM',
    subDistrict: 'Harian',
    description: 'Tersembunyi di balik perbukitan hijau Harian Boho, Air Terjun Efrata menyuguhkan hempasan air bertingkat setinggi kurang lebih 25 meter yang melebar bagaikan tirai perak alami dikelilingi hamparan sawah bertingkat dan tebing bebatuan purba.',
    shortDescription: 'Air terjun tirai setinggi 25 meter dengan suasana hutan asri dan persawahan hijau membentang.',
    ticketPrice: 7000,
    estimatedDurationHours: 1.5,
    openingHours: '07:30 - 17:30 WIB',
    latitude: 2.5642,
    longitude: 98.6658,
    imageUrl: 'https://images.unsplash.com/photo-1432405972618-c60b0225b8f9?auto=format&fit=crop&w=1200&q=80',
    facilities: 'Jalan Setapak Akses, Pondok Istirahat, Spot Foto Alami, Toilet',
    isFeatured: false,
    rating: 4.8,
    reviewCount: 98
  },
  {
    id: 5,
    name: 'Menara Pandang Tele',
    slug: 'menara-pandang-tele',
    category: 'PETUALANGAN_RELIGI',
    subDistrict: 'Harian',
    description: 'Terletak di satu-satunya jalur darat yang menghubungkan Pulau Samosir dengan daratan utama Sumatera Utara, Menara Pandang Tele adalah gardu pandang ikonik 4 lantai dengan pemandangan magis ke arah kaldera dan Pusuk Buhit.',
    shortDescription: 'Menara observasi panorama tertinggi yang memperlihatkan lekukan magis pulau Samosir dan kaldera Danau Toba.',
    ticketPrice: 10000,
    estimatedDurationHours: 1.5,
    openingHours: '06:00 - 19:00 WIB',
    latitude: 2.5519,
    longitude: 98.6503,
    imageUrl: 'https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?auto=format&fit=crop&w=1200&q=80',
    facilities: 'Gardu Pandang Bertingkat, Restoran Khas Kopi Samosir, Cinderamata, Musholla, Parkir',
    isFeatured: true,
    rating: 4.82,
    reviewCount: 188
  },
  {
    id: 6,
    name: 'Desa Tenun Ulos Lumban Suhi-Suhi',
    slug: 'desa-tenun-lumban-suhi-suhi',
    category: 'KULINER_UMKM',
    subDistrict: 'Pangururan',
    description: 'Pusat pelestarian dan produksi kain tenun Ulos tradisional Batak paling masyhur di dunia. Hampir di setiap teras rumah panggung kayu tradisional di desa ini, terdengar irama khas alat tenun kayu gedogan yang dimainkan oleh penenun wanita Batak.',
    shortDescription: 'Desa perajin kain Ulos otentik tempat menyaksikan proses penenunan benang alami di pelataran rumah adat.',
    ticketPrice: 0,
    estimatedDurationHours: 2.5,
    openingHours: '08:00 - 17:00 WIB',
    latitude: 2.6148,
    longitude: 98.7180,
    imageUrl: 'https://images.unsplash.com/photo-1607344645866-009c320c5ab8?auto=format&fit=crop&w=1200&q=80',
    facilities: 'Workshop Tenun Interaktif, Galeri Penjualan Langsung Kreator, Rumah Adat Bolon Terawat',
    isFeatured: true,
    rating: 4.92,
    reviewCount: 167
  }
];

export const FALLBACK_CREATORS: Creator[] = [
  {
    id: 1,
    name: 'Nai Sintong Sinaga',
    slug: 'nai-sintong-sinaga',
    craftType: 'Penenun Ulos Tradisional Pewarna Alam',
    village: 'Desa Lumban Suhi-Suhi, Simanindo',
    bio: 'Maestro tenun yang telah mendedikasikan hidupnya selama lebih dari 30 tahun melestarikan Ulos klasik Batak Toba dengan pewarna alami daun salaon dan kulit kayu mahoni.',
    whatsappNumber: '+6281260819451',
    isWhatsappVerified: true,
    profileImageUrl: 'https://images.unsplash.com/photo-1544005313-94ddf0286df2?auto=format&fit=crop&w=600&q=80',
    yearsOfExperience: 32,
    awards: 'Penerima Penghargaan Anugerah Desa Wisata Tenun Sumut 2023, Pembina Komunitas Tenun Samosir'
  },
  {
    id: 2,
    name: 'Amang Poltak Malau',
    slug: 'amang-poltak-malau',
    craftType: 'Pengrajin Seni Ukir Gorga Batak',
    village: 'Desa Tomok, Simanindo',
    bio: 'Pengrajin kayu generasi ketiga di Tomok yang setia mengukir ragam hias Gorga Batak seperti Singa-singa, Ipon-ipon, dan Paung pada kayu ingul dan jior.',
    whatsappNumber: '+6281375218902',
    isWhatsappVerified: true,
    profileImageUrl: 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?auto=format&fit=crop&w=600&q=80',
    yearsOfExperience: 27,
    awards: 'Sertifikasi Pelestari Seni Tradisi Kayu Batak Kemenparekraf 2022'
  },
  {
    id: 3,
    name: 'Martua Simbolon',
    slug: 'martua-simbolon',
    craftType: 'Petani & Roaster Kopi Arabica Samosir',
    village: 'Kelurahan Siogung-Ogung, Pangururan',
    bio: 'Pegiat kopi muda yang membina petani lokal di lereng Pusuk Buhit untuk membudidayakan varietas Arabica Sigarar Utang dengan proses wet hulling tradisional.',
    whatsappNumber: '+6282164392011',
    isWhatsappVerified: true,
    profileImageUrl: 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?auto=format&fit=crop&w=600&q=80',
    yearsOfExperience: 11,
    awards: 'Juara 2 Cupping Specialty Coffee Toba Caldera Fest 2023'
  },
  {
    id: 4,
    name: 'Butet Nainggolan',
    slug: 'butet-nainggolan',
    craftType: 'Pengolah Kuliner Rempah Andaliman & Hasil Toba',
    village: 'Tuk-Tuk Siadong, Simanindo',
    bio: 'Inovator UMKM kuliner yang mengangkat andaliman (merica batak hutan) menjadi produk olahan sambal segar higienis dalam kemasan ramah wisatawan.',
    whatsappNumber: '+6285270183492',
    isWhatsappVerified: true,
    profileImageUrl: 'https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?auto=format&fit=crop&w=600&q=80',
    yearsOfExperience: 8,
    awards: 'Pemenang UMKM Kuliner Inovatif Samosir Creative Hub 2024'
  }
];

export const FALLBACK_PRODUCTS: Product[] = [
  {
    id: 1,
    creator: FALLBACK_CREATORS[0],
    name: 'Ulos Ragi Hotang Pewarna Alami Eksklusif',
    slug: 'ulos-ragi-hotang-pewarna-alami',
    category: 'TENUN_ULOS',
    price: 850000,
    description: 'Ulos Ragi Hotang ditenun menggunakan benang katun halus dengan pewarna organik daun salaon liar Samosir. Memiliki makna keteguhan hati dan biasa dihadiahkan dalam upacara adat.',
    imageUrl: 'https://images.unsplash.com/photo-1607344645866-009c320c5ab8?auto=format&fit=crop&w=800&q=80',
    stockStatus: 'TERSEDIA',
    isAuthenticHandcrafted: true
  },
  {
    id: 2,
    creator: FALLBACK_CREATORS[1],
    name: 'Plakat Ukir Dinding Gorga Singa-Singa Kayu Ingul',
    slug: 'plakat-gorga-singa-singa',
    category: 'KERAJINAN_GORGA',
    price: 380000,
    description: 'Ukiran kayu khas Batak bermotif Singa-singa yang melambangkan keadilan, kepemimpinan, dan perlindungan rumah. Dipahat manual dari kayu ingul bertekstur kuat.',
    imageUrl: 'https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?auto=format&fit=crop&w=800&q=80',
    stockStatus: 'TERSEDIA',
    isAuthenticHandcrafted: true
  },
  {
    id: 3,
    creator: FALLBACK_CREATORS[2],
    name: 'Kopi Arabica Samosir Pusuk Buhit Single Origin (250g)',
    slug: 'kopi-arabica-samosir-pusuk-buhit',
    category: 'KOPI_REMPAH',
    price: 75000,
    description: 'Biji kopi Arabica dipetik merah dari lereng Pusuk Buhit di ketinggian 1.450 mdpl. Profil sangrai medium roasted dengan notes rasa rempah herbal, cokelat, dan aroma jeruk andaliman lembut.',
    imageUrl: 'https://images.unsplash.com/photo-1587734195503-904fca47e0e9?auto=format&fit=crop&w=800&q=80',
    stockStatus: 'TERSEDIA',
    isAuthenticHandcrafted: true
  },
  {
    id: 4,
    creator: FALLBACK_CREATORS[3],
    name: 'Sambal Andaliman Khas Toba Kemasan Kaca (180g)',
    slug: 'sambal-andaliman-khas-toba',
    category: 'KULINER_KHAS',
    price: 45000,
    description: 'Sambal cabe rawit segar berpadu dengan andaliman asli pegunungan Samosir yang menghadirkan sensasi getir gurih yang unik dan khas lidah Batak.',
    imageUrl: 'https://images.unsplash.com/photo-1588166524941-3bf61a9c41db?auto=format&fit=crop&w=800&q=80',
    stockStatus: 'TERSEDIA',
    isAuthenticHandcrafted: true
  }
];

export const FALLBACK_STORIES: CulturalStory[] = [
  {
    id: 1,
    title: 'Dalihan Na Tolu: Tri Tunggal Falsafah Harmoni Batak Toba',
    slug: 'dalihan-na-tolu-falsafah-hidup-batak',
    category: 'FILOSOFI',
    summary: 'Fondasi struktur sosial dan etika bermasyarakat suku Batak yang menjaga keseimbangan kekeluargaan dan penghormatan antar-marga.',
    content: 'Dalihan Na Tolu secara harfiah bermakna "tungku berkaki tiga", perlambang bahwa suatu perapian tidak akan pernah tegak kokoh tanpa ketiga tumpuannya. Dalam kebudayaan Batak Toba, falsafah ini mengatur relasi tiga kelompok kekerabatan: Somba Marhula-hula (hormat kepada pihak keluarga pemberi istri), Manat Mardongan Tubu (bersikap bijak dan hati-hati kepada kerabat semarga), serta Elek Marboru (lemah lembut dan mengayomi pihak penerima istri). Ketiganya menciptakan tatanan sosial yang egaliter sekaligus penuh rasa hormat.',
    batakQuote: 'Somba marhula-hula, manat mardongan tubu, elek marboru.',
    quoteMeaning: 'Hormat kepada keluarga pihak istri, bijaksana kepada teman semarga, dan mengayomi anak perempuan / saudara perempuan.',
    readingTimeMinutes: 6,
    coverImageUrl: 'https://images.unsplash.com/photo-1518548419970-58e3b4079ab2?auto=format&fit=crop&w=1200&q=80'
  },
  {
    id: 2,
    title: 'Misteri Tari Boneka Sigale-Gale: Dari Tangisan Ayah Menjadi Seni Agung',
    slug: 'misteri-tari-boneka-sigale-gale',
    category: 'LEGENDA',
    summary: 'Kisah keharuan Raja Rahat yang mengilhami pemahatan boneka kayu yang mampu menari untuk menghibur duka mendalam sang ayah.',
    content: 'Legenda mengisahkan bahwa Raja Rahat di tanah Samosir memiliki putra tunggal yang gagah berani bernama Datu Pejel. Ketika putranya gugur di medan peperangan mempertahankan tanah air, sang raja jatuh sakit parah akibat kesedihan yang tak tertahankan. Para penasihat kerajaan memanggil Datu (ahli spiritual dan pemahat) untuk membuat patung kayu menyerupai sang pangeran. Melalui lantunan Gondang Sabangunan dan mantra pemanggilan arwah, patung yang diberi nama Sigale-gale ini dapat menari di halaman istana, mengobati kerinduan sang raja.',
    batakQuote: 'Molo adong pe sihabiaran, anggo roha marsada pos do roha.',
    quoteMeaning: 'Bila ada duka dan kekhawatiran, hati yang bersatu dan tulus akan mendatangkan ketenteraman.',
    readingTimeMinutes: 7,
    coverImageUrl: 'https://images.unsplash.com/photo-1578632767115-351597cf2477?auto=format&fit=crop&w=1200&q=80'
  },
  {
    id: 3,
    title: 'Filosofi Ulos: Kehangatan Jiwa dan Doa Sepanjang Hayat',
    slug: 'filosofi-ulos-kehangatan-jiwa',
    category: 'TRADISI',
    summary: 'Bagi masyarakat Batak, Ulos bukan sekadar helai benang tenun, melainkan wujud perlambang kasih sayang, perlindungan, dan doa restu para leluhur.',
    content: 'Nenek moyang orang Batak meyakini tiga sumber kehangatan hidup: api, matahari, dan ulos. Di dataran tinggi Samosir yang berhawa dingin, ulos menjadi pembungkus raga dan jiwa. Ulos diberikan pada momen-momen penting dalam siklus kehidupan manusia: mulai dari tujuh bulanan kehamilan (Ulos Tondi), pernikahan (Ulos Hela), hingga wafat. Setiap motif, warna, dan pintalan memiliki tata krama pemberian (Mangulosi) yang tidak boleh tertukar.',
    batakQuote: 'Ijuk pangihot ni dame, ulos mangalehon las ni roha.',
    quoteMeaning: 'Sebagaimana ijuk mempererat tali pengikat, kain ulos menganugerahkan kehangatan dan kedamaian hati.',
    readingTimeMinutes: 5,
    coverImageUrl: 'https://images.unsplash.com/photo-1607344645866-009c320c5ab8?auto=format&fit=crop&w=1200&q=80'
  }
];

export const FALLBACK_EXPERIENCES: Experience[] = [
  {
    id: 1,
    title: 'Workshop Menenun Ulos Pewarna Alami 1 Hari',
    slug: 'workshop-tenun-ulos-1-hari',
    type: 'WORKSHOP',
    location: 'Desa Lumban Suhi-Suhi, Samosir',
    eventDate: 'Setiap Hari Selasa & Sabtu (09:00 WIB)',
    duration: '5 Jam',
    price: 175000,
    description: 'Belajar langsung dari maestro penenun Lumban Suhi-Suhi tentang pengenalan motif Batak, pencampuran pewarna indigo dan kulit kayu, hingga mempraktikkan alat tenun gedogan tradisional di teras rumah bolon.',
    organizerName: 'Komunitas Tenun Lumban Suhi-Suhi',
    whatsappBooking: '+6281260819451',
    imageUrl: 'https://images.unsplash.com/photo-1607344645866-009c320c5ab8?auto=format&fit=crop&w=1200&q=80',
    maxParticipants: 10
  },
  {
    id: 2,
    title: 'Trekking Budaya & Meditasi Fajar Pusuk Buhit',
    slug: 'trekking-budaya-pusuk-buhit',
    type: 'TUR_BUDAYA',
    location: 'Sianjur Mula-Mula, Pangururan',
    eventDate: 'Setiap Akhir Pekan (Jumat - Sabtu)',
    duration: '1 Hari (Menginap di Basecamp)',
    price: 250000,
    description: 'Menapaki jalur legenda leluhur Batak menuju puncak Pusuk Buhit diiringi kisah mitologi dari pemandu adat, menyaksikan matahari terbit di atas Danau Toba, dan singgah di Aek Sipitu Dai.',
    organizerName: 'Pemandu Budaya Pusuk Buhit Samosir',
    whatsappBooking: '+6282164392011',
    imageUrl: 'https://images.unsplash.com/photo-1519681393784-d120267933ba?auto=format&fit=crop&w=1200&q=80',
    maxParticipants: 15
  },
  {
    id: 3,
    title: 'Samosir Music International & Toba Gondang Fest 2026',
    slug: 'samosir-music-international-2026',
    type: 'FESTIVAL_AGENDA',
    location: 'Open Air Stage Tuk-Tuk Siadong',
    eventDate: '22 - 24 Agustus 2026',
    duration: '3 Hari Acara Puncak',
    price: 0,
    description: 'Festival musik internasional ikonik yang memadukan musisi mancanegara dengan orkestra Gondang Sabangunan dan lagu-lagu legendaris Batak di panggung terbuka berlatar Danau Toba.',
    organizerName: 'Dinas Pariwisata Samosir & Project Toba',
    whatsappBooking: '+6281375218902',
    imageUrl: 'https://images.unsplash.com/photo-1465847899084-d164df4dedc6?auto=format&fit=crop&w=1200&q=80',
    maxParticipants: 5000
  }
];

// HTTP Fetch Wrapper with Graceful Degradation
async function apiRequest<T>(endpoint: string, options: RequestInit = {}, fallbackData?: T): Promise<T> {
  const url = `${API_BASE}${endpoint}`;
  try {
    const res = await fetch(url, {
      ...options,
      headers: {
        'Content-Type': 'application/json',
        ...getAuthHeader(),
        ...options.headers
      }
    });

    if (!res.ok) {
      const errorJson = await res.json().catch(() => null);
      const msg = errorJson?.message || `HTTP Error ${res.status}: ${res.statusText}`;
      throw new Error(msg);
    }

    const json: ApiResponse<T> = await res.json();
    return json.data;
  } catch (err) {
    if (fallbackData !== undefined) {
      console.warn(`[SamosirVerse] Backend offline atau request gagal ke ${url}. Menggunakan data otentik Samosir fallback.`, err);
      return fallbackData;
    }
    throw err;
  }
}

// Public API Services
export const api = {
  // Destinations
  async getDestinations(category = '', subDistrict = '', search = ''): Promise<Destination[]> {
    const params = new URLSearchParams();
    if (category) params.append('category', category);
    if (subDistrict) params.append('subDistrict', subDistrict);
    if (search) params.append('search', search);
    const qs = params.toString() ? `?${params.toString()}` : '';

    return apiRequest<Destination[]>(`/destinations${qs}`, {}, FALLBACK_DESTINATIONS.filter(d => {
      const matchCat = !category || d.category === category;
      const matchSub = !subDistrict || d.subDistrict.toLowerCase() === subDistrict.toLowerCase();
      const matchSearch = !search || d.name.toLowerCase().includes(search.toLowerCase()) || d.description.toLowerCase().includes(search.toLowerCase());
      return matchCat && matchSub && matchSearch;
    }));
  },

  async getFeaturedDestinations(): Promise<Destination[]> {
    return apiRequest<Destination[]>('/destinations/featured', {}, FALLBACK_DESTINATIONS.filter(d => d.isFeatured));
  },

  async getDestinationBySlug(slug: string): Promise<Destination> {
    const fallback = FALLBACK_DESTINATIONS.find(d => d.slug === slug) || FALLBACK_DESTINATIONS[0];
    return apiRequest<Destination>(`/destinations/slug/${slug}`, {}, fallback);
  },

  // Creators
  async getCreators(craftType = '', search = ''): Promise<Creator[]> {
    const params = new URLSearchParams();
    if (craftType) params.append('craftType', craftType);
    if (search) params.append('search', search);
    const qs = params.toString() ? `?${params.toString()}` : '';

    return apiRequest<Creator[]>(`/creators${qs}`, {}, FALLBACK_CREATORS.filter(c => {
      const matchCraft = !craftType || c.craftType.toLowerCase().includes(craftType.toLowerCase());
      const matchSearch = !search || c.name.toLowerCase().includes(search.toLowerCase()) || c.village.toLowerCase().includes(search.toLowerCase());
      return matchCraft && matchSearch;
    }));
  },

  async getCreatorBySlug(slug: string): Promise<Creator> {
    const fallback = FALLBACK_CREATORS.find(c => c.slug === slug) || FALLBACK_CREATORS[0];
    return apiRequest<Creator>(`/creators/slug/${slug}`, {}, fallback);
  },

  // Products
  async getProducts(category = '', creatorId?: number, search = ''): Promise<Product[]> {
    const params = new URLSearchParams();
    if (category) params.append('category', category);
    if (creatorId) params.append('creatorId', String(creatorId));
    if (search) params.append('search', search);
    const qs = params.toString() ? `?${params.toString()}` : '';

    return apiRequest<Product[]>(`/products${qs}`, {}, FALLBACK_PRODUCTS.filter(p => {
      const matchCat = !category || p.category === category;
      const matchCreator = !creatorId || p.creator?.id === creatorId;
      const matchSearch = !search || p.name.toLowerCase().includes(search.toLowerCase());
      return matchCat && matchCreator && matchSearch;
    }));
  },

  async getProductBySlug(slug: string): Promise<Product> {
    const fallback = FALLBACK_PRODUCTS.find(p => p.slug === slug) || FALLBACK_PRODUCTS[0];
    return apiRequest<Product>(`/products/slug/${slug}`, {}, fallback);
  },

  // Cultural Stories
  async getCulturalStories(category = '', search = ''): Promise<CulturalStory[]> {
    const params = new URLSearchParams();
    if (category) params.append('category', category);
    if (search) params.append('search', search);
    const qs = params.toString() ? `?${params.toString()}` : '';

    return apiRequest<CulturalStory[]>(`/cultural-stories${qs}`, {}, FALLBACK_STORIES.filter(s => {
      const matchCat = !category || s.category === category;
      const matchSearch = !search || s.title.toLowerCase().includes(search.toLowerCase());
      return matchCat && matchSearch;
    }));
  },

  async getCulturalStoryBySlug(slug: string): Promise<CulturalStory> {
    const fallback = FALLBACK_STORIES.find(s => s.slug === slug) || FALLBACK_STORIES[0];
    return apiRequest<CulturalStory>(`/cultural-stories/slug/${slug}`, {}, fallback);
  },

  // Experiences
  async getExperiences(type = '', search = ''): Promise<Experience[]> {
    const params = new URLSearchParams();
    if (type) params.append('type', type);
    if (search) params.append('search', search);
    const qs = params.toString() ? `?${params.toString()}` : '';

    return apiRequest<Experience[]>(`/experiences${qs}`, {}, FALLBACK_EXPERIENCES.filter(e => {
      const matchType = !type || e.type === type;
      const matchSearch = !search || e.title.toLowerCase().includes(search.toLowerCase()) || e.location.toLowerCase().includes(search.toLowerCase());
      return matchType && matchSearch;
    }));
  },

  async getExperienceBySlug(slug: string): Promise<Experience> {
    const fallback = FALLBACK_EXPERIENCES.find(e => e.slug === slug) || FALLBACK_EXPERIENCES[0];
    return apiRequest<Experience>(`/experiences/slug/${slug}`, {}, fallback);
  },

  // Trip Planner Engine
  async generateTripPlan(req: TripPlanRequest): Promise<TripPlanResponse> {
    return apiRequest<TripPlanResponse>('/trip-planner/generate', {
      method: 'POST',
      body: JSON.stringify(req)
    });
  },

  // Authentication
  async login(username: string, password: string): Promise<AuthResponse> {
    return apiRequest<AuthResponse>('/auth/login', {
      method: 'POST',
      body: JSON.stringify({ username, password })
    });
  },

  // Admin Dashboard
  async getAdminStats(): Promise<DashboardStats> {
    return apiRequest<DashboardStats>('/admin/stats', {}, {
      totalDestinations: FALLBACK_DESTINATIONS.length,
      totalCreators: FALLBACK_CREATORS.length,
      totalProducts: FALLBACK_PRODUCTS.length,
      totalStories: FALLBACK_STORIES.length,
      totalExperiences: FALLBACK_EXPERIENCES.length
    });
  },

  // Admin CRUD Destinations
  async createDestination(data: Partial<Destination>): Promise<Destination> {
    return apiRequest<Destination>('/admin/destinations', {
      method: 'POST',
      body: JSON.stringify(data)
    });
  },
  async updateDestination(id: number, data: Partial<Destination>): Promise<Destination> {
    return apiRequest<Destination>(`/admin/destinations/${id}`, {
      method: 'PUT',
      body: JSON.stringify(data)
    });
  },
  async deleteDestination(id: number): Promise<void> {
    return apiRequest<void>(`/admin/destinations/${id}`, { method: 'DELETE' });
  },

  // Admin CRUD Creators
  async createCreator(data: Partial<Creator>): Promise<Creator> {
    return apiRequest<Creator>('/admin/creators', {
      method: 'POST',
      body: JSON.stringify(data)
    });
  },
  async updateCreator(id: number, data: Partial<Creator>): Promise<Creator> {
    return apiRequest<Creator>(`/admin/creators/${id}`, {
      method: 'PUT',
      body: JSON.stringify(data)
    });
  },
  async deleteCreator(id: number): Promise<void> {
    return apiRequest<void>(`/admin/creators/${id}`, { method: 'DELETE' });
  },

  // Admin CRUD Products
  async createProduct(data: any): Promise<Product> {
    return apiRequest<Product>('/admin/products', {
      method: 'POST',
      body: JSON.stringify(data)
    });
  },
  async updateProduct(id: number, data: any): Promise<Product> {
    return apiRequest<Product>(`/admin/products/${id}`, {
      method: 'PUT',
      body: JSON.stringify(data)
    });
  },
  async deleteProduct(id: number): Promise<void> {
    return apiRequest<void>(`/admin/products/${id}`, { method: 'DELETE' });
  },

  // Admin CRUD Stories
  async createStory(data: Partial<CulturalStory>): Promise<CulturalStory> {
    return apiRequest<CulturalStory>('/admin/cultural-stories', {
      method: 'POST',
      body: JSON.stringify(data)
    });
  },
  async updateStory(id: number, data: Partial<CulturalStory>): Promise<CulturalStory> {
    return apiRequest<CulturalStory>(`/admin/cultural-stories/${id}`, {
      method: 'PUT',
      body: JSON.stringify(data)
    });
  },
  async deleteStory(id: number): Promise<void> {
    return apiRequest<void>(`/admin/cultural-stories/${id}`, { method: 'DELETE' });
  },

  // Admin CRUD Experiences
  async createExperience(data: Partial<Experience>): Promise<Experience> {
    return apiRequest<Experience>('/admin/experiences', {
      method: 'POST',
      body: JSON.stringify(data)
    });
  },
  async updateExperience(id: number, data: Partial<Experience>): Promise<Experience> {
    return apiRequest<Experience>(`/admin/experiences/${id}`, {
      method: 'PUT',
      body: JSON.stringify(data)
    });
  },
  async deleteExperience(id: number): Promise<void> {
    return apiRequest<void>(`/admin/experiences/${id}`, { method: 'DELETE' });
  }
};
