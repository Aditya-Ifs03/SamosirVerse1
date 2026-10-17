<script lang="ts">
  import type { Experience } from '$lib/types';

  export let experience: Experience;

  function formatPrice(val: number): string {
    if (val === 0) return 'Gratis / Terbuka';
    return `Rp ${val.toLocaleString('id-ID')}`;
  }

  function formatType(t: string): string {
    switch (t) {
      case 'WORKSHOP': return 'Workshop Kriya';
      case 'FESTIVAL_AGENDA': return 'Festival Seni';
      case 'TUR_BUDAYA': return 'Tur Jelajah Budaya';
      case 'KULINER': return 'Kelas Kuliner Batak';
      default: return t;
    }
  }

  function getBookingUrl(exp: Experience): string {
    const cleanNumber = exp.whatsappBooking.replace(/[^0-9]/g, '');
    const msg = encodeURIComponent(`Halo ${exp.organizerName}, saya ingin mendaftar untuk kegiatan "${exp.title}" (${exp.eventDate}) yang saya temukan di SamosirVerse. Mohon informasi ketersediaan slot peserta.`);
    return `https://wa.me/${cleanNumber}?text=${msg}`;
  }
</script>

<article class="experience-card card-editorial">
  <div class="exp-img-box">
    <img src={experience.imageUrl} alt={experience.title} class="exp-img" loading="lazy" />
    <span class="exp-type badge badge-forest">{formatType(experience.type)}</span>
    <span class="exp-date-pill">📅 {experience.eventDate}</span>
  </div>

  <div class="exp-content">
    <div class="exp-meta-top">
      <span class="exp-location">📍 {experience.location}</span>
      <span class="exp-duration">⏱ {experience.duration}</span>
    </div>

    <h3 class="exp-title">{experience.title}</h3>

    <p class="exp-desc">{experience.description}</p>

    <div class="organizer-info">
      <span>Penyelenggara: <strong>{experience.organizerName}</strong></span>
      {#if experience.maxParticipants}
        <span class="slots-text">Kuota: {experience.maxParticipants} orang</span>
      {/if}
    </div>

    <div class="exp-footer">
      <div class="exp-price-box">
        <span class="exp-price-lbl">Biaya Partisipasi</span>
        <span class="exp-price-val">{formatPrice(experience.price)}</span>
      </div>

      <a
        href={getBookingUrl(experience)}
        target="_blank"
        rel="noopener noreferrer"
        class="btn btn-whatsapp btn-sm"
      >
        <span>💬 Reservasi via WA</span>
      </a>
    </div>
  </div>
</article>

<style>
  .experience-card {
    display: flex;
    flex-direction: column;
    height: 100%;
  }

  .exp-img-box {
    position: relative;
    width: 100%;
    height: 220px;
    background-color: var(--color-ivory-dark);
  }

  .exp-img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .exp-type {
    position: absolute;
    top: 1rem;
    left: 1rem;
    background: rgba(18, 60, 54, 0.9);
    color: var(--color-ivory);
    backdrop-filter: blur(6px);
  }

  .exp-date-pill {
    position: absolute;
    bottom: 1rem;
    left: 1rem;
    background: rgba(255, 255, 255, 0.92);
    color: var(--color-forest);
    font-size: 0.78rem;
    font-weight: 700;
    padding: 0.35rem 0.75rem;
    border-radius: var(--radius-full);
    backdrop-filter: blur(6px);
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  }

  .exp-content {
    display: flex;
    flex-direction: column;
    flex: 1;
    padding: 1.5rem;
  }

  .exp-meta-top {
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 0.8rem;
    color: var(--color-muted);
    font-weight: 600;
    margin-bottom: 0.6rem;
  }

  .exp-title {
    font-size: 1.25rem;
    line-height: 1.3;
    margin-bottom: 0.6rem;
    color: var(--color-forest);
  }

  .exp-desc {
    font-size: 0.88rem;
    color: var(--color-charcoal);
    line-height: 1.55;
    margin-bottom: 1.25rem;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }

  .organizer-info {
    font-size: 0.8rem;
    color: var(--color-muted);
    margin-bottom: 1.25rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0.6rem 0.85rem;
    background: var(--color-ivory);
    border-radius: var(--radius-sm);
  }

  .organizer-info strong {
    color: var(--color-forest);
  }

  .exp-footer {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-top: auto;
    padding-top: 1rem;
    border-top: 1px solid var(--color-border);
    gap: 0.5rem;
  }

  .exp-price-box {
    display: flex;
    flex-direction: column;
  }

  .exp-price-lbl {
    font-size: 0.7rem;
    text-transform: uppercase;
    color: var(--color-muted);
    font-weight: 600;
  }

  .exp-price-val {
    font-size: 1.05rem;
    font-weight: 700;
    color: var(--color-terracotta);
  }
</style>
