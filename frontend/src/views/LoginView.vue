<script setup lang="ts">import { ref } from 'vue';
import {useLogin} from '@/services/useLogin'

useLogin().logout()
const benutzername = ref("")
const passwort = ref("")
</script>

<template>
<h1>Bitte loggen Sie sich ein:</h1>
<input type="text" v-model="benutzername" placeholder="Benutzername"/>
<input type="text" v-model="passwort" placeholder="Passwort"/>
<button v-on:click="useLogin().login(benutzername, passwort)">login</button>
</template>