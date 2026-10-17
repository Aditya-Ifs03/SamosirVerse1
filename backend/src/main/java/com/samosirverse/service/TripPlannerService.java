package com.samosirverse.service;

import com.samosirverse.dto.TripPlanRequest;
import com.samosirverse.dto.TripPlanResponse;
import com.samosirverse.entity.Destination;
import com.samosirverse.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripPlannerService {

    private final DestinationRepository destinationRepository;

    public TripPlannerService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public TripPlanResponse generatePlan(TripPlanRequest request) {
        int days = request.getDurationDays() != null ? Math.min(Math.max(request.getDurationDays(), 1), 3) : 2;
        String style = request.getTravelStyle() != null ? request.getTravelStyle().toUpperCase() : "BUDAYA";
        String budget = request.getBudgetCategory() != null ? request.getBudgetCategory().toUpperCase() : "STANDAR";
        String group = request.getGroupType() != null ? request.getGroupType().toUpperCase() : "PASANGAN";

        TripPlanResponse response = new TripPlanResponse();
        response.setDurationDays(days);
        response.setTravelStyle(style);
        response.setBudgetCategory(budget);
        response.setGroupType(group);

        // Dynamic Title & Summary
        String titlePrefix = switch (style) {
            case "ALAM" -> "Eksplorasi Panorama Kaldera & Savana Hijau Samosir";
            case "SANTAI" -> "Pelesir Damai & Teduh Pesisir Danau Toba Samosir";
            case "KULINER_KREATIF" -> "Jelajah Karya Maestro, Kopi Pusuk Buhit & Rempah Andaliman";
            default -> "Jejak Peradaban Leluhur & Warisan Sakral Batak Toba";
        };
        response.setPlanTitle(titlePrefix + " (" + days + " Hari)");

        response.setSummary("Itinerary cerdas berbasis geolokasi dan ritme perjalanan autentik di Pulau Samosir untuk "
                + formatGroupText(group) + " dengan preferensi gaya " + style.toLowerCase()
                + " dan tingkatan kenyamanan " + budget.toLowerCase() + ".");

        // Budget Calculations
        BigDecimal dailyLivingCost = switch (budget) {
            case "HEMAT" -> new BigDecimal("150000"); // Transport hemat + makan warung lokal
            case "PREMIUM" -> new BigDecimal("750000"); // Private transport + luxury lakeside resto
            default -> new BigDecimal("350000"); // Motor/mobil sewa + kafe/resto nyaman
        };

        BigDecimal totalTickets = BigDecimal.ZERO;
        List<TripPlanResponse.DayPlan> dayPlans = new ArrayList<>();

        // Generate day-by-day itineraries based on duration and style
        for (int d = 1; d <= days; d++) {
            TripPlanResponse.DayPlan dayPlan = buildDayPlan(d, days, style);
            for (TripPlanResponse.ActivityItem act : dayPlan.getActivities()) {
                if (act.getEstimatedCost() != null) {
                    totalTickets = totalTickets.add(act.getEstimatedCost());
                }
            }
            dayPlans.add(dayPlan);
        }

        response.setDays(dayPlans);

        BigDecimal totalLivingCost = dailyLivingCost.multiply(BigDecimal.valueOf(days));
        BigDecimal grandTotal = totalTickets.add(totalLivingCost);
        response.setTotalEstimatedCost(grandTotal);

        response.setCostBreakdown(String.format(
                "Estimasi Total: Rp %,d (Tiket Masuk & Retribusi: Rp %,d | Akomodasi, Transportasi & Konsumsi %s: Rp %,d)",
                grandTotal.longValue(), totalTickets.longValue(), budget, totalLivingCost.longValue()
        ));

        // Cultural Etiquette Guidelines
        List<String> tips = new ArrayList<>();
        tips.add("Awali perjumpaan dengan warga lokal dengan senyum ramah dan ucapan salam hangat 'Horas!'");
        tips.add("Saat memasuki situs sakral seperti Huta Siallagan dan Makam Raja Sidabutar, kenakan selendang ulos dan hindari berkata kotor.");
        tips.add("Ketika mengunjungi Desa Tenun Lumban Suhi-Suhi, mintalah izin terlebih dahulu dengan santun sebelum mengambil foto penenun yang sedang bekerja.");
        tips.add("Dukung ekonomi kerakyatan dengan membeli karya asli UMKM (ulos, ukiran kayu gorga, kopi, andaliman) secara langsung dari perajin.");
        tips.add("Jaga kebersihan pulau dengan tidak membuang sampah plastik ke perairan Danau Toba maupun di punggungan bukit savana.");
        response.setCulturalEtiquetteTips(tips);

        return response;
    }

    private TripPlanResponse.DayPlan buildDayPlan(int dayNumber, int totalDays, String style) {
        TripPlanResponse.DayPlan day = new TripPlanResponse.DayPlan();
        day.setDayNumber(dayNumber);

        if (dayNumber == 1) {
            day.setTheme("Pintu Gerbang Tradisi & Kearifan Pesisir Timur Samosir");
            day.getActivities().add(new TripPlanResponse.ActivityItem(
                    "08:30 - 10:30 WIB", "PAGI",
                    "Ziarah Sejarah Makam Kuno Raja Sidabutar & Tari Sigale-gale",
                    "Desa Wisata Tomok, Simanindo",
                    "Memasuki gapura Tomok, mengenakan ulos selendang penghormatan di makam batu monolit raja pertama, lalu menyaksikan pertunjukan boneka kayu sakral Sigale-gale yang menari diiringi Gondang Sabangunan.",
                    new BigDecimal("10000"),
                    "Bawa uang tunai kecil untuk saweran tari Sigale-Gale sebagai tanda apresiasi musisi gondang.",
                    2L
            ));

            day.getActivities().add(new TripPlanResponse.ActivityItem(
                    "11:00 - 13:00 WIB", "SIANG",
                    "Eksplorasi Peradilan Adat Purba di Huta Siallagan",
                    "Ambarita, Simanindo",
                    "Melihat langsung kursi dan meja Batu Parsidangan di bawah naungan pohon Hariara keramat, tempat raja Batak mengadili kejahatan masa lampau dan melestarikan filosofi Runggun (musyawarah).",
                    new BigDecimal("10000"),
                    "Dengarkan penjelasan pemandu lokal huta untuk memahami konteks hukum adat yang penuh nilai filosofis.",
                    1L
            ));

            day.getActivities().add(new TripPlanResponse.ActivityItem(
                    "14:30 - 17:00 WIB", "SORE",
                    "Menyusuri Jejak Wastra di Desa Tenun Ulos Lumban Suhi-Suhi",
                    "Lumban Suhi-Suhi, Pangururan",
                    "Menyaksikan maestro penenun merajut motif Ulos Ragi Hotang dan Sadum dengan pewarna indigo alami langsung di teras rumah panggung kayu tradisional.",
                    new BigDecimal("0"),
                    "Anda dapat mencoba memegang alat tenun kayu gedogan dan berdialog dengan ibu-ibu penenun.",
                    6L
            ));

            day.getActivities().add(new TripPlanResponse.ActivityItem(
                    "18:30 - 21:00 WIB", "MALAM",
                    "Makan Malam Kuliner Ikan Mas Danau Toba & Santai Tepi Danau",
                    "Semenanjung Tuk-Tuk Siadong",
                    "Menikmati santap malam olahan khas Ikan Mas Bumbu Arsik Andaliman segar di restoran tepi danau dengan tiupan semilir angin dan alunan petikan gitar Batak.",
                    new BigDecimal("65000"),
                    "Pesan sambal andaliman segar untuk sensasi getir rempah hutan khas Batak yang kaya antioksidan.",
                    null
            ));
        } else if (dayNumber == 2) {
            day.setTheme("Kemegahan Kaldera Vulkanik, Air Terjun & Savana Sunset");
            day.getActivities().add(new TripPlanResponse.ActivityItem(
                    "07:30 - 09:30 WIB", "PAGI",
                    "Pemandangan 360 Derajat Kaldera di Menara Pandang Tele",
                    "Kecamatan Harian",
                    "Menatap hamparan biru Danau Toba dan lekukan Pulau Samosir dari titik tertinggi jalan darat Tele yang menghubungkan pulau ke daratan Sumatera.",
                    new BigDecimal("10000"),
                    "Waktu terbaik berkunjung adalah pagi hari sebelum kabut tebal menyelimuti lembah.",
                    5L
            ));

            day.getActivities().add(new TripPlanResponse.ActivityItem(
                    "10:15 - 12:30 WIB", "SIANG",
                    "Gemuruh Sejuk Tirai Perak Air Terjun Efrata",
                    "Sosor Dolok, Harian",
                    "Menikmati kesegaran air terjun tirai setinggi 25 meter yang membelah tebing hutan rimbun, dikelilingi pemandangan sawah terasering berundak.",
                    new BigDecimal("7000"),
                    "Gunakan alas kaki anti selip karena bebatuan sekitar air terjun cenderung basah dan licin.",
                    4L
            ));

            day.getActivities().add(new TripPlanResponse.ActivityItem(
                    "13:30 - 15:30 WIB", "SIANG",
                    "Misteri Danau Sidihoni (Danau Unik di Atas Danau)",
                    "Kecamatan Pangururan",
                    "Mengunjungi keajaiban geologis danau air tawar alami yang berada di tengah pulau di pedalaman perbukitan Samosir.",
                    new BigDecimal("5000"),
                    "Tempat yang sangat tenang untuk rehat sejenak dan menikmati udara sejuk dataran tinggi.",
                    7L
            ));

            day.getActivities().add(new TripPlanResponse.ActivityItem(
                    "16:30 - 18:30 WIB", "SORE",
                    "Menyambut Senja Emas di Perbukitan Savana Bukit Holbung",
                    "Kecamatan Sitio-tio",
                    "Trekking santai menyusuri bukit savana hijau bergelombang sembari menyaksikan matahari terbenam memancarkan rona keemasan di permukaan Danau Toba.",
                    new BigDecimal("10000"),
                    "Bawa jaket penahan angin dan kamera untuk mengabadikan momen matahari terbenam terbaik di Samosir.",
                    3L
            ));
        } else {
            day.setTheme("Mata Air Sakral, Asal Usul Pusuk Buhit & Belanja Karya Kreator");
            day.getActivities().add(new TripPlanResponse.ActivityItem(
                    "08:00 - 10:30 WIB", "PAGI",
                    "Trekking Kaki Gunung Pusuk Buhit & Mata Air Tujuh Rasa Aek Sipitu Dai",
                    "Sianjur Mula-Mula",
                    "Mendatangi tanah mitologi asal-usul orang Batak di kaki Pusuk Buhit dan membasuh wajah di mata air sakral Aek Sipitu Dai yang memiliki rasa tawar berbeda di tiap pancuran.",
                    new BigDecimal("15000"),
                    "Patuhi norma adat setempat dan hargai pantangan yang dijelaskan oleh tetua kampung.",
                    8L
            ));

            day.getActivities().add(new TripPlanResponse.ActivityItem(
                    "11:00 - 13:00 WIB", "SIANG",
                    "Cupping & Edukasi Kopi Arabica Pusuk Buhit",
                    "Siogung-Ogung, Pangururan",
                    "Mencicipi kopi Arabica specialty varietas Sigarar Utang yang ditanam di tanah vulkanik lereng Pusuk Buhit bersama roaster lokal.",
                    new BigDecimal("35000"),
                    "Anda dapat membeli biji kopi sangrai segar langsung dari petaninya sebagai buah tangan premium.",
                    null
            ));

            day.getActivities().add(new TripPlanResponse.ActivityItem(
                    "14:00 - 16:30 WIB", "SORE",
                    "Galeri Cinderamata Karya Maestro & Pemandian Air Panas Pangururan",
                    "Pangururan, Samosir",
                    "Membeli plakat ukiran gorga Batak Singa-singa, selendang tenun, dan bersantai merendam kaki di pemandian air panas alami belerang sebelum menyeberang pulang.",
                    new BigDecimal("15000"),
                    "Kemas barang belanjaan dengan rapi dan pastikan nomor kontak kreator tersimpan untuk pemesanan ulang.",
                    null
            ));
        }

        return day;
    }

    private String formatGroupText(String group) {
        return switch (group) {
            case "SOLO" -> "Wisatawan Mandiri (Solo Traveler)";
            case "KELUARGA" -> "Rombongan Keluarga & Anak-anak";
            case "ROMBONGAN" -> "Grup Wisata / Komunitas";
            default -> "Wisatawan Berpasangan";
        };
    }
}
