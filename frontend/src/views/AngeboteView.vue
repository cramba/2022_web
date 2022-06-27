<script setup lang="ts">
import { useAngebot, updateAngebote, receiveAngebotMessages } from '@/services/useAngebot';

import AngebotListe from '../components/AngebotListe.vue'

const {angebote} = useAngebot()
receiveAngebotMessages()
const errorMessage = angebote.errormessage
let anzAngebote = angebote.angebotliste.length
</script>

<template>
<h2>Wir haben gerade {{angebote.angebotliste.length}} Angebote für Sie</h2> <br/>
    <div v-if ="angebote.errormessage">{{angebote.errormessage}}</div>
    <AngebotListe></AngebotListe>
    <button v-on:click="updateAngebote()">Reload</button>
</template>