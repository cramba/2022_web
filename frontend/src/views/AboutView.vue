<script setup lang="ts">
import GeoLink from '@/components/GeoLink.vue'
</script>

<template>
  <div class="about">
    <h1>This is an about page</h1><br/><br/>
    
    <GeoLink :lat="52.9849" :lon="9.5794">Visselhövede</GeoLink>
  </div>
  
</template>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
