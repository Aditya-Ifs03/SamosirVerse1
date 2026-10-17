<script lang="ts">
  import type { CulturalStory } from '$lib/types';

  export let story: CulturalStory;

  function formatCategory(cat: string): string {
    switch (cat) {
      case 'FILOSOFI': return 'Falsafah Hidup';
      case 'LEGENDA': return 'Legenda & Mitos';
      case 'TRADISI': return 'Tradisi & Wastra';
      case 'SITUS_SEJARAH': return 'Situs Bersejarah';
      default: return cat;
    }
  }
</script>

<article class="story-card card-editorial">
  <a href="/budaya/{story.slug}" class="story-img-link">
    <div class="story-image-box">
      <img src={story.coverImageUrl} alt={story.title} class="story-img" loading="lazy" />
      <span class="story-category badge badge-forest">{formatCategory(story.category)}</span>
      <span class="reading-time">⏱ {story.readingTimeMinutes} mnt baca</span>
    </div>
  </a>

  <div class="story-content">
    <h3 class="story-title">
      <a href="/budaya/{story.slug}">{story.title}</a>
    </h3>

    <p class="story-summary">{story.summary}</p>

    {#if story.batakQuote}
      <blockquote class="batak-quote-box">
        <p class="quote-text">"{story.batakQuote}"</p>
        {#if story.quoteMeaning}
          <span class="quote-meaning">— {story.quoteMeaning}</span>
        {/if}
      </blockquote>
    {/if}

    <div class="story-footer">
      <a href="/budaya/{story.slug}" class="read-more-link">
        <span>Baca Selengkapnya &rarr;</span>
      </a>
    </div>
  </div>
</article>

<style>
  .story-card {
    display: flex;
    flex-direction: column;
    height: 100%;
  }

  .story-img-link {
    display: block;
    overflow: hidden;
  }

  .story-image-box {
    position: relative;
    width: 100%;
    height: 220px;
    background-color: var(--color-ivory-dark);
  }

  .story-img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: transform 0.5s ease;
  }

  .story-card:hover .story-img {
    transform: scale(1.05);
  }

  .story-category {
    position: absolute;
    top: 1rem;
    left: 1rem;
    background: rgba(18, 60, 54, 0.9);
    color: var(--color-ivory);
    backdrop-filter: blur(6px);
  }

  .reading-time {
    position: absolute;
    bottom: 1rem;
    right: 1rem;
    background: rgba(255, 255, 255, 0.9);
    color: var(--color-forest);
    font-size: 0.75rem;
    font-weight: 600;
    padding: 0.25rem 0.6rem;
    border-radius: var(--radius-full);
    backdrop-filter: blur(6px);
  }

  .story-content {
    display: flex;
    flex-direction: column;
    flex: 1;
    padding: 1.5rem;
  }

  .story-title {
    font-size: 1.25rem;
    line-height: 1.35;
    margin-bottom: 0.75rem;
  }

  .story-title a {
    color: var(--color-forest);
    transition: color var(--transition-fast);
  }

  .story-title a:hover {
    color: var(--color-terracotta);
  }

  .story-summary {
    font-size: 0.88rem;
    color: var(--color-muted);
    line-height: 1.6;
    margin-bottom: 1rem;
  }

  .batak-quote-box {
    background-color: var(--color-ivory);
    border-left: 3px solid var(--color-terracotta);
    padding: 0.75rem 1rem;
    border-radius: 0 var(--radius-sm) var(--radius-sm) 0;
    margin-bottom: 1.25rem;
  }

  .quote-text {
    font-family: var(--font-serif);
    font-style: italic;
    font-size: 0.88rem;
    color: var(--color-forest);
    line-height: 1.4;
    margin-bottom: 0.25rem;
  }

  .quote-meaning {
    font-size: 0.76rem;
    color: var(--color-muted);
    display: block;
  }

  .story-footer {
    margin-top: auto;
    padding-top: 0.75rem;
  }

  .read-more-link {
    font-size: 0.88rem;
    font-weight: 700;
    color: var(--color-terracotta);
    display: inline-flex;
    align-items: center;
    gap: 0.35rem;
    transition: transform var(--transition-fast);
  }

  .read-more-link:hover {
    transform: translateX(4px);
  }
</style>
