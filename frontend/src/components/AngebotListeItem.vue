<script setup lang="ts">
import type { IAngebotListeItem } from '@/services/IAngebotListeItem';
import {ref} from "vue"

const props = defineProps<{
  //angebot :  IAngebotListeItem
}>()

//let beschreibung = props.angebot.beschreibung;
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
    <table>
        <thead>
            <th style="width:60%">Beschreibung</th>
            <th style="width:10%">Gebote</th>
            <th style="width:10%">Topgebot</th>
            <th style="width:10%"><Button @click="toggleTable()">aufklappen</Button></th>
        </thead>
        <tbody v-if="showTable">
            <tr>Hallo</tr>
            <tr>Test</tr>
            <tr>Text</tr>
            <tr>Text</tr>
        </tbody>
    </table>
</template>