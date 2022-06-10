<script setup lang="ts">
import AngebotListeItem from '@/components/AngebotListeItem.vue'
import { useFakeAngebot } from '@/services/useFakeAngebot'
import { ref, computed } from 'vue'
let angebotliste = useFakeAngebot().angebote

const suchfeld = ref("")

function clearSuchfeld(){
    suchfeld.value = ""
}

const angebotefiltered = computed(() => {
    const n: number = suchfeld.value.length;
    if(suchfeld.value.length < 3) {
        return angebotliste.value
    } else {
        return angebotliste.value.filter(e =>
            e.beschreibung.toLowerCase().includes(suchfeld.value.toLocaleLowerCase())||
            e.abholort.toLowerCase().includes(suchfeld.value.toLocaleLowerCase())||
            e.anbietername.toLowerCase().includes(suchfeld.value.toLocaleLowerCase())
                );
    }
});
</script>

<template>
    <input type="text" v-model="suchfeld" 
        placeholder="Suchbegriff" />
    <button @click="clearSuchfeld()">clear</button>
    <table>
        <tbody>
            <AngebotListeItem :angebot="ele"
            v-for="ele in angebotefiltered" :key="ele.angebotid"></AngebotListeItem>
        </tbody>
    </table>
</template>