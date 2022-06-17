<script setup lang="ts">
import type { IAngebotListeItem } from '@/services/IAngebotListeItem';
import GeoLink from '@/components/GeoLink.vue'
import {ref} from "vue"

const props = defineProps<{
  angebot :  IAngebotListeItem
}>()


let showTable = ref(false);
function toggleTable(){
    if(showTable.value){
        showTable.value = false;
       
    }else{
        showTable.value = true;
    }
}

</script>

<template>
    <table class="steelBlueColors">
        <thead>
            <th style="width:40%">{{props.angebot.beschreibung}}</th>
            <th style="width:20%">{{props.angebot.gebote}} Gebote</th>
            <th style="width:20%">{{props.angebot.topgebot}} EUR</th>
            <th v-if="showTable" style="width:20%"><Button id="showInfo" @click="toggleTable()">zuklappen</Button></th>
            <th v-else style="width:20%"><Button id="showInfo" @click="toggleTable()">aufklappen</Button></th>
        </thead>
        <tbody v-if="showTable">
            <tr>Letztes Gebot   {{props.angebot.topgebot}}EUR (Mindespreis war {{props.angebot.mindestpreis}}EUR)</tr>
            <tr>Abholort <GeoLink :lat="props.angebot.lat" :lon="props.angebot.lon">{{props.angebot.abholort}}</GeoLink></tr>
            <tr>bei {{props.angebot.anbietername}}</tr>
            <tr>bis {{props.angebot.ablaufzeitpunkt.toLocaleDateString()}} um {{props.angebot.ablaufzeitpunkt.toLocaleTimeString()}} Uhr</tr>
        </tbody>
    </table>
</template>