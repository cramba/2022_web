<script setup lang="ts">
import GeoLink from '@/components/GeoLink.vue'
import AngebotListeItem from '../components/AngebotListeItem.vue'
import type { IAngebotListeItem } from '@/services/IAngebotListeItem';
let a : IAngebotListeItem;
</script>

<template>
  <div>
    
    <GeoLink :lat="52.9849" :lon="9.5794">Visselhövede</GeoLink>
    <br/><br/>
    <AngebotListeItem :angebot="a" />
    
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
