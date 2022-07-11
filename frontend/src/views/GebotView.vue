<script setup lang="ts">
import { useAngebot } from '@/services/useAngebot'
import { useGebot } from '@/services/useGebot'
import GeoLink from '@/components/GeoLink.vue'
import { ref } from 'vue';
const props = defineProps<{
    angebotidstr : string
}>()

const angebotid = parseInt(props.angebotidstr)
const {angebote} = useAngebot()
let angebot = angebote.angebotliste.find(x => x.angebotid === angebotid)
if (angebot == undefined) {
    angebot = angebote.angebotliste[0]
}

const {gebote} = useGebot(angebotid)

const preisinput = ref("")
</script>

<template>
<h2>Versteigerung{{angebot!.beschreibung}}</h2>
<p>von {{angebot!.anbietername}}, abholbar in  <GeoLink :lat="angebot!.lat" :lon="angebot!.lon">{{angebot!.abholort}}</GeoLink> bis {{angebot!.ablaufzeitpunkt}}</p>
<div v-if ="angebote.errormessage">{{angebote.errormessage}}</div>
<br/>
<p>Bisheriges Topgebot von {{gebote.topgebot}} ist von {{gebote.topbieter}}</p>
<input type="number" v-model="preisinput" placeholder="Gebot"/>
<button v-on:click="useGebot(angebotid).sendeGebot(parseInt(preisinput))">bieten</button>
</template>