<script lang="ts">
  import type { Product } from '$lib/types';

  export let product: Product;

  function formatPrice(val: number): string {
    return `Rp ${val.toLocaleString('id-ID')}`;
  }

  function formatCategory(cat: string): string {
    switch (cat) {
      case 'TENUN_ULOS': return 'Tenun Ulos Otentik';
      case 'KERAJINAN_GORGA': return 'Ukir Kayu Gorga';
      case 'KOPI_REMPAH': return 'Kopi Arabica Samosir';
      case 'KULINER_KHAS': return 'Kuliner & Andaliman';
      default: return cat;
    }
  }

  function getOrderUrl(prod: Product): string {
    const wa = prod.creator?.whatsappNumber || '+6281260819451';
    const cleanNumber = wa.replace(/[^0-9]/g, '');
    const msg = encodeURIComponent(`Halo, saya ingin memesan produk "${prod.name}" (Harga: ${formatPrice(prod.price)}) yang tertera di platform SamosirVerse. Apakah stok masih tersedia?`);
    return `https://wa.me/${cleanNumber}?text=${msg}`;
  }
</script>

<article class="product-card card-editorial">
  <div class="product-image-box">
    <img src={product.imageUrl} alt={product.name} class="product-img" loading="lazy" />
    <span class="product-cat badge badge-forest">{formatCategory(product.category)}</span>
    {#if product.isAuthenticHandcrafted}
      <span class="authentic-tag badge badge-terracotta">100% Buatan Tangan</span>
    {/if}
  </div>

  <div class="product-content">
    {#if product.creator}
      <div class="creator-attribution">
        <span>Oleh: <strong>{product.creator.name}</strong> ({product.creator.village})</span>
      </div>
    {/if}

    <h3 class="product-title">{product.name}</h3>

    <p class="product-desc">{product.description}</p>

    <div class="product-footer">
      <div class="price-box">
        <span class="price-lbl">Harga Produk</span>
        <span class="price-amount">{formatPrice(product.price)}</span>
      </div>

      <a
        href={getOrderUrl(product)}
        target="_blank"
        rel="noopener noreferrer"
        class="btn btn-whatsapp btn-sm order-btn"
      >
        <span>💬 Pesan via WA</span>
      </a>
    </div>
  </div>
</article>

<style>
  .product-card {
    display: flex;
    flex-direction: column;
    height: 100%;
  }

  .product-image-box {
    position: relative;
    width: 100%;
    height: 220px;
    background-color: var(--color-ivory-dark);
    overflow: hidden;
  }

  .product-img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: transform 0.4s ease;
  }

  .product-card:hover .product-img {
    transform: scale(1.05);
  }

  .product-cat {
    position: absolute;
    top: 0.75rem;
    left: 0.75rem;
    background: rgba(18, 60, 54, 0.9);
    color: var(--color-ivory);
    backdrop-filter: blur(6px);
  }

  .authentic-tag {
    position: absolute;
    bottom: 0.75rem;
    left: 0.75rem;
    background: rgba(201, 110, 72, 0.92);
    color: white;
    backdrop-filter: blur(6px);
  }

  .product-content {
    display: flex;
    flex-direction: column;
    flex: 1;
    padding: 1.25rem;
  }

  .creator-attribution {
    font-size: 0.78rem;
    color: var(--color-muted);
    margin-bottom: 0.35rem;
  }

  .creator-attribution strong {
    color: var(--color-forest);
  }

  .product-title {
    font-size: 1.15rem;
    line-height: 1.3;
    margin-bottom: 0.5rem;
    color: var(--color-forest);
  }

  .product-desc {
    font-size: 0.85rem;
    color: var(--color-charcoal);
    line-height: 1.5;
    margin-bottom: 1.25rem;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }

  .product-footer {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-top: auto;
    padding-top: 0.85rem;
    border-top: 1px solid var(--color-border);
    gap: 0.5rem;
  }

  .price-box {
    display: flex;
    flex-direction: column;
  }

  .price-lbl {
    font-size: 0.7rem;
    text-transform: uppercase;
    color: var(--color-muted);
    font-weight: 600;
  }

  .price-amount {
    font-size: 1.05rem;
    font-weight: 700;
    color: var(--color-forest);
  }

  .order-btn {
    white-space: nowrap;
  }
</style>
