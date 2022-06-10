<script setup lang="ts">
import AngebotListeItem from '../components/AngebotListeItem.vue'
import type { IAngebotListeItem } from '@/services/IAngebotListeItem'
import { useFakeAngebot} from '@/services/useFakeAngebot'
import AngebotListe from '../components/AngebotListe.vue'

//let a : IAngebotListeItem
//a = useFakeAngebot().angebote.value[0]
</script>

<template>
  <div>
    

    <!--<AngebotListeItem :angebot="a" />-->
    <AngebotListe></AngebotListe>
    <button @click="useFakeAngebot().bietenSimulieren()">Fake Bieten</button>
    
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
