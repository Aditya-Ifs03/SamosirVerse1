<script lang="ts">
  import type { Creator } from '$lib/types';

  export let creator: Creator;

  function getWhatsAppUrl(phone: string, name: string): string {
    const cleanNumber = phone.replace(/[^0-9]/g, '');
    const message = encodeURIComponent(`Halo ${name}, saya melihat profil Anda di platform SamosirVerse. Saya tertarik dengan karya kerajinan Anda.`);
    return `https://wa.me/${cleanNumber}?text=${message}`;
  }
</script>

<article class="creator-card card-editorial">
  <div class="creator-header">
    <div class="avatar-wrapper">
      <img src={creator.profileImageUrl} alt={creator.name} class="creator-avatar" loading="lazy" />
      {#if creator.isWhatsappVerified}
        <span class="verified-dot" title="Nomor WhatsApp Terverifikasi Resmi">✓</span>
      {/if}
    </div>

    <div class="header-info">
      <div class="craft-badge badge badge-lake">{creator.craftType}</div>
      <h3 class="creator-name">
        <a href="/kreator/{creator.slug}">{creator.name}</a>
      </h3>
      <span class="creator-village">📍 {creator.village}</span>
    </div>
  </div>

  <div class="creator-body">
    <p class="creator-bio">
      {creator.bio}
    </p>

    <div class="creator-stats-row">
      <div class="stat-pill">
        <span class="stat-num">{creator.yearsOfExperience}+ Tahun</span>
        <span class="stat-lbl">Pengalaman Berkarya</span>
      </div>
      {#if creator.awards}
        <div class="awards-preview" title={creator.awards}>
          🏆 {creator.awards.split(',')[0]}
        </div>
      {/if}
    </div>

    <div class="card-action-row">
      <a href="/kreator/{creator.slug}" class="btn btn-secondary btn-sm flex-1">
        Lihat Profil
      </a>
      <a
        href={getWhatsAppUrl(creator.whatsappNumber, creator.name)}
        target="_blank"
        rel="noopener noreferrer"
        class="btn btn-whatsapp btn-sm flex-1"
      >
        <span>💬 Chat WhatsApp</span>
      </a>
    </div>
  </div>
</article>

<style>
  .creator-card {
    display: flex;
    flex-direction: column;
    padding: 1.5rem;
    height: 100%;
  }

  .creator-header {
    display: flex;
    align-items: flex-start;
    gap: 1.25rem;
    margin-bottom: 1.25rem;
  }

  .avatar-wrapper {
    position: relative;
    flex-shrink: 0;
  }

  .creator-avatar {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    object-fit: cover;
    border: 3px solid var(--color-lake-light);
    box-shadow: 0 4px 12px rgba(18, 60, 54, 0.12);
  }

  .verified-dot {
    position: absolute;
    bottom: 2px;
    right: 2px;
    width: 22px;
    height: 22px;
    background-color: #25D366;
    color: white;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 0.75rem;
    font-weight: bold;
    border: 2px solid white;
  }

  .header-info {
    flex: 1;
  }

  .craft-badge {
    margin-bottom: 0.4rem;
    font-size: 0.68rem;
  }

  .creator-name {
    font-size: 1.2rem;
    line-height: 1.2;
    margin-bottom: 0.35rem;
  }

  .creator-name a {
    color: var(--color-forest);
    transition: color var(--transition-fast);
  }

  .creator-name a:hover {
    color: var(--color-terracotta);
  }

  .creator-village {
    font-size: 0.8rem;
    color: var(--color-muted);
    font-weight: 500;
  }

  .creator-body {
    display: flex;
    flex-direction: column;
    flex: 1;
  }

  .creator-bio {
    font-size: 0.88rem;
    color: var(--color-charcoal);
    line-height: 1.6;
    margin-bottom: 1.25rem;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }

  .creator-stats-row {
    margin-top: auto;
    margin-bottom: 1.25rem;
    display: flex;
    flex-direction: column;
    gap: 0.5rem;
  }

  .stat-pill {
    display: flex;
    align-items: baseline;
    gap: 0.5rem;
  }

  .stat-num {
    font-weight: 700;
    color: var(--color-terracotta);
    font-size: 0.92rem;
  }

  .stat-lbl {
    font-size: 0.78rem;
    color: var(--color-muted);
  }

  .awards-preview {
    font-size: 0.76rem;
    color: var(--color-forest);
    background-color: var(--color-ivory-dark);
    padding: 0.35rem 0.65rem;
    border-radius: var(--radius-sm);
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .card-action-row {
    display: flex;
    gap: 0.6rem;
  }

  .flex-1 {
    flex: 1;
    text-align: center;
  }
</style>
