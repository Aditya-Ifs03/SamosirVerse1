<script lang="ts">
  import '$lib/styles/app.css';
  import Navbar from '$lib/components/Navbar.svelte';
  import Footer from '$lib/components/Footer.svelte';
  import ToastContainer from '$lib/components/ToastContainer.svelte';
</script>

<div class="app-layout">
  <Navbar />
  <main class="main-content">
    <slot />
  </main>
  <Footer />
  <ToastContainer />
</div>

<style>
  .app-layout {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
  }

  .main-content {
    flex: 1;
  }
</style>
