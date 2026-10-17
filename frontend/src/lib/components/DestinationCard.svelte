<script lang="ts">
  import type { Destination } from '$lib/types';

  export let destination: Destination;

  function formatPrice(price: number): string {
    if (price === 0) return 'Gratis / Sukarela';
    return `Rp ${price.toLocaleString('id-ID')}`;
  }

  function formatCategory(cat: string): string {
    switch (cat) {
      case 'BUDAYA_SEJARAH': return 'Budaya & Sejarah';
      case 'ALAM': return 'Panorama Alam';
      case 'KULINER_UMKM': return 'Kreator & Tenun';
      case 'PETUALANGAN_RELIGI': return 'Petualangan & Sakral';
      default: return cat;
    }
  }
</script>

<article class="destination-card card-editorial">
  <a href="/destinasi/{destination.slug}" class="card-image-link">
    <div class="image-wrapper">
      <img src={destination.imageUrl} alt={destination.name} loading="lazy" class="card-img" />
      <div class="category-badge badge badge-forest">{formatCategory(destination.category)}</div>
      <div class="rating-badge">
        <span class="star-icon">★</span>
        <span class="rating-value">{destination.rating.toFixed(1)}</span>
      </div>
    </div>
  </a>

  <div class="card-content">
    <div class="location-row">
      <span class="location-pin">📍</span>
      <span class="location-subdistrict">{destination.subDistrict}, Samosir</span>
    </div>

    <h3 class="card-title">
      <a href="/destinasi/{destination.slug}">{destination.name}</a>
    </h3>

    <p class="card-excerpt">
      {destination.shortDescription}
    </p>

    <div class="meta-divider"></div>

    <div class="meta-footer">
      <div class="price-col">
        <span class="meta-label">Tiket Masuk</span>
        <span class="price-val">{formatPrice(destination.ticketPrice)}</span>
      </div>

      <div class="duration-col">
        <span class="meta-label">Durasi Kunjungan</span>
        <span class="duration-val">⏱ {destination.estimatedDurationHours} Jam</span>
      </div>

      <a href="/destinasi/{destination.slug}" class="btn-detail" aria-label="Lihat {destination.name}">
        <span>Lihat &rarr;</span>
      </a>
    </div>
  </div>
</article>

<style>
  .destination-card {
    display: flex;
    flex-direction: column;
    height: 100%;
  }

  .card-image-link {
    display: block;
    overflow: hidden;
  }

  .image-wrapper {
    position: relative;
    width: 100%;
    height: 230px;
    overflow: hidden;
    background-color: var(--color-ivory-dark);
  }

  .card-img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: transform 0.5s cubic-bezier(0.16, 1, 0.3, 1);
  }

  .destination-card:hover .card-img {
    transform: scale(1.06);
  }

  .category-badge {
    position: absolute;
    top: 1rem;
    left: 1rem;
    background: rgba(18, 60, 54, 0.88);
    color: var(--color-ivory);
    backdrop-filter: blur(8px);
    font-size: 0.72rem;
    letter-spacing: 0.05em;
  }

  .rating-badge {
    position: absolute;
    bottom: 1rem;
    right: 1rem;
    display: flex;
    align-items: center;
    gap: 0.25rem;
    background: rgba(255, 255, 255, 0.92);
    backdrop-filter: blur(8px);
    padding: 0.3rem 0.65rem;
    border-radius: var(--radius-full);
    font-size: 0.82rem;
    font-weight: 700;
    color: var(--color-forest);
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  }

  .star-icon {
    color: #F59E0B;
  }

  .card-content {
    display: flex;
    flex-direction: column;
    flex: 1;
    padding: 1.5rem;
  }

  .location-row {
    display: flex;
    align-items: center;
    gap: 0.35rem;
    font-size: 0.82rem;
    color: var(--color-muted);
    font-weight: 600;
    margin-bottom: 0.5rem;
  }

  .card-title {
    font-size: 1.25rem;
    margin-bottom: 0.6rem;
    line-height: 1.3;
  }

  .card-title a {
    color: var(--color-forest);
    transition: color var(--transition-fast);
  }

  .card-title a:hover {
    color: var(--color-terracotta);
  }

  .card-excerpt {
    font-size: 0.88rem;
    color: var(--color-muted);
    line-height: 1.55;
    margin-bottom: 1.25rem;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }

  .meta-divider {
    margin-top: auto;
    height: 1px;
    background-color: var(--color-border);
    margin-bottom: 1rem;
  }

  .meta-footer {
    display: flex;
    align-items: center;
    justify-content: space-between;
    gap: 0.5rem;
  }

  .meta-label {
    display: block;
    font-size: 0.7rem;
    text-transform: uppercase;
    color: var(--color-muted);
    font-weight: 600;
    letter-spacing: 0.04em;
  }

  .price-val {
    font-size: 0.92rem;
    font-weight: 700;
    color: var(--color-forest);
  }

  .duration-val {
    font-size: 0.85rem;
    font-weight: 600;
    color: var(--color-charcoal);
  }

  .btn-detail {
    font-size: 0.86rem;
    font-weight: 700;
    color: var(--color-terracotta);
    padding: 0.4rem 0.6rem;
    border-radius: var(--radius-sm);
    transition: all var(--transition-fast);
  }

  .btn-detail:hover {
    background-color: rgba(201, 110, 72, 0.1);
    transform: translateX(2px);
  }
</style>
