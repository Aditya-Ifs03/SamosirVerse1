export interface Destination {
  id: number;
  name: string;
  slug: string;
  category: 'ALAM' | 'BUDAYA_SEJARAH' | 'KULINER_UMKM' | 'PETUALANGAN_RELIGI' | string;
  subDistrict: string;
  description: string;
  shortDescription: string;
  culturalSignificance?: string;
  ticketPrice: number;
  estimatedDurationHours: number;
  openingHours: string;
  latitude: number;
  longitude: number;
  imageUrl: string;
  galleryUrls?: string;
  facilities?: string;
  isFeatured: boolean;
  rating: number;
  reviewCount: number;
  createdAt?: string;
  updatedAt?: string;
}

export interface Creator {
  id: number;
  name: string;
  slug: string;
  craftType: string;
  village: string;
  bio: string;
  whatsappNumber: string;
  isWhatsappVerified: boolean;
  profileImageUrl: string;
  yearsOfExperience: number;
  awards?: string;
  products?: Product[];
  createdAt?: string;
  updatedAt?: string;
}

export interface Product {
  id: number;
  creator?: Creator;
  name: string;
  slug: string;
  category: string;
  price: number;
  description: string;
  imageUrl: string;
  stockStatus: string;
  isAuthenticHandcrafted: boolean;
  createdAt?: string;
  updatedAt?: string;
}

export interface CulturalStory {
  id: number;
  title: string;
  slug: string;
  category: string;
  summary: string;
  content: string;
  batakQuote?: string;
  quoteMeaning?: string;
  readingTimeMinutes: number;
  coverImageUrl: string;
  relatedDestination?: Destination;
  createdAt?: string;
  updatedAt?: string;
}

export interface Experience {
  id: number;
  title: string;
  slug: string;
  type: string;
  location: string;
  eventDate: string;
  duration: string;
  price: number;
  description: string;
  organizerName: string;
  whatsappBooking: string;
  imageUrl: string;
  maxParticipants: number;
  createdAt?: string;
  updatedAt?: string;
}

export interface ActivityItem {
  timeSlot: string;
  period: string;
  title: string;
  location: string;
  description: string;
  estimatedCost: number;
  tips: string;
  destinationId?: number;
}

export interface DayPlan {
  dayNumber: number;
  theme: string;
  activities: ActivityItem[];
}

export interface TripPlanResponse {
  planTitle: string;
  summary: string;
  durationDays: number;
  travelStyle: string;
  budgetCategory: string;
  groupType: string;
  totalEstimatedCost: number;
  costBreakdown: string;
  days: DayPlan[];
  culturalEtiquetteTips: string[];
}

export interface TripPlanRequest {
  durationDays: number;
  travelStyle: string;
  budgetCategory: string;
  groupType: string;
}

export interface AuthResponse {
  token: string;
  tokenType: string;
  id: number;
  username: string;
  email: string;
  fullName: string;
  role: string;
}

export interface DashboardStats {
  totalDestinations: number;
  totalCreators: number;
  totalProducts: number;
  totalStories: number;
  totalExperiences: number;
}

export interface ApiResponse<T> {
  success: boolean;
  message: string;
  data: T;
  timestamp?: string;
}
