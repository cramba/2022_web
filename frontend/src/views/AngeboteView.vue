<script setup lang="ts">
import { useAngebot } from '@/services/useAngebot';

import AngebotListe from '../components/AngebotListe.vue'

const {angebote} = useAngebot()
let errorMessage = angebote.errormessage
let anzAngebote = angebote.angebotliste.length
</script>

<template>
<h2>Wir haben gerade {{anzAngebote}} Angebote für Sie</h2> <br/>
    <div v-if ="angebote.errormessage">{{angebote.errormessage}}</div>
    <AngebotListe></AngebotListe>
</template>