<script lang="ts">
  import { page } from '$app/stores';
  import { authStore, clearSession } from '$lib/stores/auth';
  import { showToast } from '$lib/stores/toast';

  let mobileMenuOpen = false;

  function isActive(path: string): boolean {
    const currentPath = $page.url.pathname.replace(/\\/+$/, '') || '/';
    const targetPath = path.replace(/\\/+$/, '') || '/';
    return targetPath === '/' ? currentPath === '/' : currentPath === targetPath || currentPath.startsWith(targetPath + '/');
  }

  function toggleMobile() {
    mobileMenuOpen = !mobileMenuOpen;
  }

  function handleLogout() {
    clearSession();
    showToast('Anda telah berhasil keluar dari akun admin.', 'info');
  }
</script>

<header class="navbar-wrapper">
  <div class="navbar-container container">
    <!-- Brand Logo -->
    <a href="/" class="brand-logo">
      <span class="brand-symbol">🌿</span>
      <div class="brand-text">
        <span class="brand-title">Samosir<span class="brand-accent">Verse</span></span>
        <span class="brand-tagline">Creative Hub Samosir</span>
      </div>
    </a>

    <!-- Desktop Navigation -->
    <nav class="nav-links">
      <a href="/" class="nav-link" class:active={isActive('/')}>Beranda</a>
      <a href="/destinasi" class="nav-link" class:active={isActive('/destinasi')}>Destinasi</a>
      <a href="/peta" class="nav-link" class:active={isActive('/peta')}>Peta Samosir</a>
      <a href="/trip-planner" class="nav-link" class:active={isActive('/trip-planner')}>Trip Planner</a>
      <a href="/budaya" class="nav-link" class:active={isActive('/budaya')}>Budaya Batak</a>
      <a href="/kreator" class="nav-link" class:active={isActive('/kreator')}>Kreator & UMKM</a>
      <a href="/agenda" class="nav-link" class:active={isActive('/agenda')}>Agenda</a>
    </nav>

    <!-- Right Actions -->
    <div class="nav-actions">
      <a href="/trip-planner" class="btn btn-primary btn-sm nav-cta">
        <span>✨ Susun Rute</span>
      </a>

      {#if $authStore.isAuthenticated}
        <div class="admin-menu">
          <a href="/admin/dashboard" class="btn btn-forest btn-sm">
            <span>⚙️ Dashboard</span>
          </a>
          <button on:click={handleLogout} class="btn-logout" title="Keluar">
            🚪
          </button>
        </div>
      {:else}
        <a href="/admin/login" class="admin-login-link" title="Masuk Admin">
          <span>Admin</span>
        </a>
      {/if}

      <!-- Mobile Hamburger Button -->
      <button class="hamburger-btn" on:click={toggleMobile} aria-label="Menu">
        <span class="hamburger-line {mobileMenuOpen ? 'open-1' : ''}"></span>
        <span class="hamburger-line {mobileMenuOpen ? 'open-2' : ''}"></span>
        <span class="hamburger-line {mobileMenuOpen ? 'open-3' : ''}"></span>
      </button>
    </div>
  </div>

  <!-- Mobile Drawer -->
  {#if mobileMenuOpen}
    <div class="mobile-drawer animate-fade-in">
      <a href="/" class="mobile-link" class:active={isActive('/')} on:click={toggleMobile}>Beranda</a>
      <a href="/destinasi" class="mobile-link" class:active={isActive('/destinasi')} on:click={toggleMobile}>Katalog Destinasi</a>
      <a href="/peta" class="mobile-link" class:active={isActive('/peta')} on:click={toggleMobile}>Peta Interaktif</a>
      <a href="/trip-planner" class="mobile-link" class:active={isActive('/trip-planner')} on:click={toggleMobile}>Trip Planner Cerdas</a>
      <a href="/budaya" class="mobile-link" class:active={isActive('/budaya')} on:click={toggleMobile}>Cerita Budaya Batak</a>
      <a href="/kreator" class="mobile-link" class:active={isActive('/kreator')} on:click={toggleMobile}>Kreator Lokal & UMKM</a>
      <a href="/agenda" class="mobile-link" class:active={isActive('/agenda')} on:click={toggleMobile}>Agenda & Festival</a>
      <div class="mobile-divider"></div>
      {#if $authStore.isAuthenticated}
        <a href="/admin/dashboard" class="btn btn-forest btn-sm" on:click={toggleMobile}>Buka Dashboard Admin</a>
        <button on:click={() => { handleLogout(); toggleMobile(); }} class="btn btn-secondary btn-sm" style="margin-top: 0.5rem;">Keluar</button>
      {:else}
        <a href="/admin/login" class="mobile-link" on:click={toggleMobile}>Login Administrator</a>
      {/if}
    </div>
  {/if}
</header>

<style>
  .navbar-wrapper {
    position: sticky;
    top: 0;
    z-index: 1000;
    background: rgba(246, 241, 231, 0.92);
    backdrop-filter: blur(12px);
    border-bottom: 1px solid rgba(220, 211, 197, 0.8);
    transition: all var(--transition-normal);
  }

  .navbar-container {
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 74px;
  }

  .brand-logo {
    display: flex;
    align-items: center;
    gap: 0.75rem;
    text-decoration: none;
  }

  .brand-symbol {
    font-size: 1.8rem;
    filter: drop-shadow(0 2px 4px rgba(18, 60, 54, 0.2));
  }

  .brand-text {
    display: flex;
    flex-direction: column;
  }

  .brand-title {
    font-family: var(--font-serif);
    font-size: 1.45rem;
    font-weight: 700;
    color: var(--color-forest);
    line-height: 1.1;
  }

  .brand-accent {
    color: var(--color-terracotta);
  }

  .brand-tagline {
    font-size: 0.72rem;
    font-weight: 600;
    color: var(--color-muted);
    letter-spacing: 0.06em;
    text-transform: uppercase;
  }

  .nav-links {
    display: flex;
    align-items: center;
    gap: 1.75rem;
  }

  .nav-link {
    font-size: 0.92rem;
    font-weight: 600;
    color: var(--color-forest);
    transition: color var(--transition-fast);
    position: relative;
    padding: 0.35rem 0;
  }

  .nav-link:hover {
    color: var(--color-terracotta);
  }

  .nav-link.active {
    color: var(--color-terracotta);
    font-weight: 700;
  }

  .nav-link.active::after {
    content: '';
    position: absolute;
    left: 0;
    right: 0;
    bottom: -0.75rem;
    height: 3px;
    border-radius: 999px;
    background: var(--color-terracotta);
  }

  .nav-actions {
    display: flex;
    align-items: center;
    gap: 1rem;
  }

  .admin-login-link {
    font-size: 0.82rem;
    font-weight: 600;
    color: var(--color-muted);
    padding: 0.4rem 0.75rem;
    border-radius: var(--radius-sm);
    transition: all var(--transition-fast);
  }

  .admin-login-link:hover {
    color: var(--color-forest);
    background-color: rgba(18, 60, 54, 0.06);
  }

  .admin-menu {
    display: flex;
    align-items: center;
    gap: 0.5rem;
  }

  .btn-logout {
    font-size: 1.1rem;
    padding: 0.4rem;
    border-radius: var(--radius-sm);
    transition: transform var(--transition-fast);
  }

  .btn-logout:hover {
    transform: scale(1.15);
  }

  .hamburger-btn {
    display: none;
    flex-direction: column;
    justify-content: space-between;
    width: 24px;
    height: 18px;
    background: none;
    border: none;
    cursor: pointer;
  }

  .hamburger-line {
    width: 100%;
    height: 2px;
    background-color: var(--color-forest);
    transition: all 0.25s ease;
  }

  .open-1 {
    transform: translateY(8px) rotate(45deg);
  }
  .open-2 {
    opacity: 0;
  }
  .open-3 {
    transform: translateY(-8px) rotate(-45deg);
  }

  .mobile-drawer {
    display: flex;
    flex-direction: column;
    padding: 1.25rem 1.5rem;
    background-color: var(--color-ivory-light);
    border-top: 1px solid var(--color-border);
    gap: 1rem;
  }

  .mobile-link {
    font-size: 1rem;
    font-weight: 600;
    color: var(--color-forest);
  }

  .mobile-link.active {
    color: var(--color-terracotta);
    font-weight: 700;
  }

  .mobile-divider {
    height: 1px;
    background-color: var(--color-border);
    margin: 0.5rem 0;
  }

  @media (max-width: 992px) {
    .nav-links, .nav-cta {
      display: none;
    }
    .hamburger-btn {
      display: flex;
    }
  }
</style>
