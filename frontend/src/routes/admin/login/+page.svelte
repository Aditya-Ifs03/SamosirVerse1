<script lang="ts">
  import { onMount } from 'svelte';
  import { goto } from '$app/navigation';
  import { api } from '$lib/api';
  import { authStore, setSession } from '$lib/stores/auth';
  import { showToast } from '$lib/stores/toast';

  let username = 'admin';
  let password = '';
  let loading = false;
  let errorMessage = '';

  onMount(() => {
    if ($authStore.isAuthenticated) {
      goto('/admin/dashboard');
    }
  });

  async function handleLogin() {
    loading = true;
    errorMessage = '';
    try {
      const authData = await api.login(username, password);
      setSession(authData);
      showToast(`Selamat datang kembali, ${authData.fullName}!`, 'success');
      goto('/admin/dashboard');
    } catch (err: any) {
      errorMessage = err.message || 'Login gagal. Periksa username dan password Anda.';
      showToast(errorMessage, 'error');
    } finally {
      loading = false;
    }
  }

  function fillDemo() {
    username = 'admin';
    password = 'admin123';
  }
</script>

<svelte:head>
  <title>Login Administrator — SamosirVerse</title>
</svelte:head>

<div class="login-page">
  <div class="container container-narrow">
    <div class="login-card card-editorial animate-fade-in">
      <div class="login-header text-center">
        <span class="login-brand-symbol">🌿</span>
        <h1 class="login-title">Portal Administrator</h1>
        <p class="login-subtitle">
          Masuk untuk mengelola data destinasi wisata, kurasi kreator lokal, produk UMKM, dan cerita budaya SamosirVerse.
        </p>
      </div>

      {#if errorMessage}
        <div class="error-banner">
          <span>⚠️ {errorMessage}</span>
        </div>
      {/if}

      <form on:submit|preventDefault={handleLogin} class="login-form">
        <div class="form-group">
          <label class="form-label" for="username-input">Username atau Email Admin</label>
          <input
            id="username-input"
            type="text"
            class="form-input"
            placeholder="admin"
            bind:value={username}
            required
          />
        </div>

        <div class="form-group">
          <label class="form-label" for="password-input">Kata Sandi</label>
          <input
            id="password-input"
            type="password"
            class="form-input"
            placeholder="••••••••"
            bind:value={password}
            required
          />
        </div>

        <button type="submit" class="btn btn-primary w-full login-btn" disabled={loading}>
          {#if loading}
            <span>Memverifikasi Kredensial...</span>
          {:else}
            <span>🔐 Masuk ke Dashboard</span>
          {/if}
        </button>
      </form>

      <!-- Demo Credentials Helper Banner -->
      <div class="demo-credentials-box">
        <div class="demo-header">
          <span class="badge badge-lake">Akun Demonstrasi Pengujian</span>
          <button type="button" class="btn-fill-demo" on:click={fillDemo}>
            Isi Otomatis
          </button>
        </div>
        <div class="demo-credentials-content">
          <p><strong>Username:</strong> <code>admin</code></p>
          <p><strong>Password:</strong> <code>admin123</code></p>
          <span class="demo-note">*Kredensial bawaan sistem yang terenkripsi BCrypt pada database SamosirVerse.</span>
        </div>
      </div>

      <div class="login-footer text-center">
        <a href="/" class="back-home-link">&larr; Kembali ke Beranda Publik</a>
      </div>
    </div>
  </div>
</div>

<style>
  .login-page {
    min-height: calc(100vh - 160px);
    display: flex;
    align-items: center;
    padding: 3rem 0;
  }

  .login-card {
    max-width: 480px;
    margin: 0 auto;
    padding: 2.75rem;
    background-color: var(--color-white);
  }

  .text-center {
    text-align: center;
  }

  .login-brand-symbol {
    font-size: 2.5rem;
    display: block;
    margin-bottom: 0.5rem;
  }

  .login-title {
    font-size: 2rem;
    margin-bottom: 0.5rem;
  }

  .login-subtitle {
    font-size: 0.9rem;
    color: var(--color-muted);
    line-height: 1.5;
    margin-bottom: 1.75rem;
  }

  .error-banner {
    background-color: #FEE2E2;
    border: 1px solid #F87171;
    color: #B91C1C;
    padding: 0.75rem 1rem;
    border-radius: var(--radius-sm);
    font-size: 0.88rem;
    font-weight: 500;
    margin-bottom: 1.5rem;
  }

  .login-form {
    margin-bottom: 1.75rem;
  }

  .login-btn {
    margin-top: 0.5rem;
    padding: 0.85rem;
  }

  .w-full {
    width: 100%;
  }

  .demo-credentials-box {
    background: var(--color-ivory);
    border: 1px dashed var(--color-border);
    border-radius: var(--radius-md);
    padding: 1rem 1.25rem;
    margin-bottom: 1.5rem;
  }

  .demo-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 0.6rem;
  }

  .btn-fill-demo {
    font-size: 0.75rem;
    font-weight: 700;
    color: var(--color-terracotta);
    text-decoration: underline;
    cursor: pointer;
    background: none;
    border: none;
  }

  .demo-credentials-content p {
    font-size: 0.82rem;
    color: var(--color-charcoal);
    margin-bottom: 0.2rem;
  }

  .demo-credentials-content code {
    background: rgba(0, 0, 0, 0.06);
    padding: 0.15rem 0.4rem;
    border-radius: 4px;
    font-family: monospace;
    color: var(--color-forest);
    font-weight: bold;
  }

  .demo-note {
    display: block;
    font-size: 0.72rem;
    color: var(--color-muted);
    margin-top: 0.4rem;
  }

  .back-home-link {
    font-size: 0.88rem;
    color: var(--color-muted);
    font-weight: 600;
    transition: color var(--transition-fast);
  }

  .back-home-link:hover {
    color: var(--color-forest);
  }
</style>
