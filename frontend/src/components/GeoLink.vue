<script setup lang="ts">
import { ref } from "vue"
const props = withDefaults
    defineProps<{lat: number, lon: number, zoom?: number}>(),
    {zoom: 17}
</script>


<template>
</template>