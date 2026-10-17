<script lang="ts">
  import { onMount } from 'svelte';
  import { page } from '$app/stores';
  import { api } from '$lib/api';
  import type { Creator, Product } from '$lib/types';
  import ProductCard from '$lib/components/ProductCard.svelte';

  let creator: Creator | null = null;
  let products: Product[] = [];
  let loading = true;
  let errorMsg = '';

  $: slug = $page.params.slug;

  onMount(async () => {
    try {
      creator = await api.getCreatorBySlug(slug);
      if (creator) {
        products = await api.getProducts('', creator.id);
      }
    } catch (err: any) {
      errorMsg = err.message || 'Gagal memuat profil kreator';
    } finally {
      loading = false;
    }
  });

  function getWhatsAppUrl(phone: string, name: string): string {
    const cleanNumber = phone.replace(/[^0-9]/g, '');
    const message = encodeURIComponent(`Horas ${name}! Saya melihat profil karya Anda di platform SamosirVerse dan berminat untuk memesan kerajinan / berkunjung ke galeri Anda.`);
    return `https://wa.me/${cleanNumber}?text=${message}`;
  }
</script>

<svelte:head>
  <title>{creator ? `${creator.name} — Kreator SamosirVerse` : 'Profil Kreator — SamosirVerse'}</title>
</svelte:head>

<div class="creator-detail-page">
  {#if loading}
    <div class="state-container">
      <div class="spinner"></div>
      <p>Memuat profil maestro seni Samosir...</p>
    </div>
  {:else if errorMsg || !creator}
    <div class="state-container">
      <h2>Profil Kreator Tidak Ditemukan</h2>
      <p>{errorMsg || 'Maaf, data kreator tidak tersedia.'}</p>
      <a href="/kreator" class="btn btn-primary btn-sm">&larr; Kembali ke Katalog Kreator</a>
    </div>
  {:else}
    <div class="container">
      <a href="/kreator" class="back-link">
        <span>&larr; Kembali ke Direktori Kreator</span>
      </a>

      <!-- Profile Header Card -->
      <div class="creator-profile-card card-editorial">
        <div class="profile-header-grid">
          <div class="profile-avatar-box">
            <img src={creator.profileImageUrl} alt={creator.name} class="profile-avatar-img" />
            {#if creator.isWhatsappVerified}
              <div class="verified-pill badge badge-verified">
                <span>✓ WhatsApp Terverifikasi</span>
              </div>
            {/if}
          </div>

          <div class="profile-main-info">
            <span class="badge badge-lake">{creator.craftType}</span>
            <h1 class="creator-title-name">{creator.name}</h1>
            <p class="creator-location-text">📍 {creator.village}</p>

            <div class="experience-strip">
              <div class="exp-badge">
                <span class="exp-num">{creator.yearsOfExperience}+ Tahun</span>
                <span class="exp-lbl">Dedikasi Melestarikan Warisan</span>
              </div>
              {#if creator.awards}
                <div class="awards-badge">
                  <span class="award-icon">🏆</span>
                  <span>{creator.awards}</span>
                </div>
              {/if}
            </div>

            <div class="whatsapp-cta-box">
              <a
                href={getWhatsAppUrl(creator.whatsappNumber, creator.name)}
                target="_blank"
                rel="noopener noreferrer"
                class="btn btn-whatsapp"
              >
                <span>💬 Hubungi Langsung via WhatsApp ({creator.whatsappNumber})</span>
              </a>
              <span class="wa-helper-text">Terhubung langsung ke sanggar/workshop perajin tanpa potongan komisi.</span>
            </div>
          </div>
        </div>

        <div class="profile-divider"></div>

        <div class="biography-section">
          <h2 class="section-title">Dedikasi & Filosofi Berkarya</h2>
          <p class="bio-text">{creator.bio}</p>
        </div>
      </div>

      <!-- Products Produced by Creator -->
      <section class="creator-products-section">
        <div class="products-header">
          <div>
            <span class="badge badge-forest">Karya & Produk Otentik</span>
            <h2 class="products-title">Katalog Karya Buatan {creator.name}</h2>
          </div>
          <span class="products-count">{products.length} Produk Tersedia</span>
        </div>

        {#if products.length === 0}
          <div class="empty-products card-editorial">
            <p>Produk untuk perajin ini sedang dalam proses pembaruan katalog fisik.</p>
            <a
              href={getWhatsAppUrl(creator.whatsappNumber, creator.name)}
              target="_blank"
              rel="noopener noreferrer"
              class="btn btn-whatsapp btn-sm"
            >
              Tanyakan Ketersediaan Karya Khusus via WA
            </a>
          </div>
        {:else}
          <div class="products-grid">
            {#each products as product (product.id)}
              <ProductCard {product} />
            {/each}
          </div>
        {/if}
      </section>
    </div>
  {/if}
</div>

<style>
  .creator-detail-page {
    padding: 3rem 0 5rem;
  }

  .back-link {
    display: inline-block;
    font-size: 0.9rem;
    font-weight: 600;
    color: var(--color-muted);
    margin-bottom: 1.5rem;
    transition: color var(--transition-fast);
  }

  .back-link:hover {
    color: var(--color-forest);
  }

  .creator-profile-card {
    padding: 2.5rem;
    margin-bottom: 3.5rem;
    background-color: var(--color-white);
  }

  .profile-header-grid {
    display: grid;
    grid-template-columns: 240px 1fr;
    gap: 2.5rem;
    align-items: center;
  }

  .profile-avatar-box {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 0.75rem;
  }

  .profile-avatar-img {
    width: 200px;
    height: 200px;
    border-radius: 50%;
    object-fit: cover;
    border: 4px solid var(--color-lake-light);
    box-shadow: var(--shadow-md);
  }

  .creator-title-name {
    font-size: 2.5rem;
    line-height: 1.2;
    margin: 0.5rem 0 0.35rem;
  }

  .creator-location-text {
    font-size: 1rem;
    color: var(--color-muted);
    font-weight: 600;
    margin-bottom: 1.25rem;
  }

  .experience-strip {
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    gap: 1.5rem;
    margin-bottom: 1.75rem;
    background: var(--color-ivory);
    padding: 1rem 1.25rem;
    border-radius: var(--radius-md);
  }

  .exp-num {
    font-weight: 800;
    color: var(--color-terracotta);
    font-size: 1.2rem;
    display: block;
    line-height: 1.1;
  }

  .exp-lbl {
    font-size: 0.75rem;
    color: var(--color-muted);
    text-transform: uppercase;
    font-weight: 600;
  }

  .awards-badge {
    font-size: 0.86rem;
    font-weight: 600;
    color: var(--color-forest);
    display: flex;
    align-items: center;
    gap: 0.4rem;
  }

  .whatsapp-cta-box {
    display: flex;
    flex-direction: column;
    gap: 0.4rem;
    align-items: flex-start;
  }

  .wa-helper-text {
    font-size: 0.78rem;
    color: var(--color-muted);
  }

  .profile-divider {
    height: 1px;
    background-color: var(--color-border);
    margin: 2rem 0;
  }

  .section-title {
    font-size: 1.5rem;
    margin-bottom: 0.75rem;
    color: var(--color-forest);
  }

  .bio-text {
    font-size: 1.05rem;
    line-height: 1.8;
    color: var(--color-charcoal);
  }

  .creator-products-section {
    margin-top: 2rem;
  }

  .products-header {
    display: flex;
    align-items: flex-end;
    justify-content: space-between;
    margin-bottom: 2rem;
  }

  .products-title {
    font-size: 2rem;
    margin-top: 0.35rem;
  }

  .products-count {
    font-size: 0.9rem;
    font-weight: 600;
    color: var(--color-muted);
  }

  .products-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 2rem;
  }

  .empty-products {
    padding: 3rem;
    text-align: center;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 1rem;
    color: var(--color-muted);
  }

  .state-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 6rem 1rem;
    text-align: center;
    gap: 1rem;
  }

  .spinner {
    width: 40px;
    height: 40px;
    border: 3px solid var(--color-border);
    border-top-color: var(--color-forest);
    border-radius: 50%;
    animation: spin 0.8s linear infinite;
  }

  @keyframes spin {
    to { transform: rotate(360deg); }
  }

  @media (max-width: 992px) {
    .profile-header-grid {
      grid-template-columns: 1fr;
      text-align: center;
    }
    .profile-avatar-box {
      margin: 0 auto;
    }
    .experience-strip {
      justify-content: center;
    }
    .whatsapp-cta-box {
      align-items: center;
    }
    .products-grid {
      grid-template-columns: repeat(2, 1fr);
    }
  }

  @media (max-width: 640px) {
    .products-grid {
      grid-template-columns: 1fr;
    }
    .creator-title-name {
      font-size: 2rem;
    }
  }
</style>
