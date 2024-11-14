package com.filleuxstudio.appandroidcocktail.firebase.data

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.filleuxstudio.appandroidcocktail.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

// Service pour gérer les messages Firebase Cloud Messaging
class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        const val TAG = "FirebaseMessagingService" // Tag pour la journalisation des messages
    }

    // Méthode appelée lorsque l'application reçoit un message
    @Override
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Vérifie si le message contient des données
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data) // Affiche les données dans les logs
        }
        // Vérifie si le message contient une notification
        if (remoteMessage.notification != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body) // Affichage de la notification dans les logs
            // Envoie une notification locale à l'utilisateur
            remoteMessage.notification?.let { sendNotification(it) }
        }
    }

    // Fonction pour envoyer une notification locale à l'utilisateur
    @SuppressLint("MissingPermission") // Supprime l'avertissement de permission manquante
    private fun sendNotification(remoteMessageNotification: RemoteMessage.Notification) {

        // Crée un intent pour ouvrir MainActivity lorsque la notification est cliquée
        val intent = Intent(this, MainActivity::class.java).also {
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Supprime toutes les activités au-dessus de l'activité cible
        }
        val channelId = "myNotificationChannel"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_menu_gallery) // Icône de la notification
            .setContentTitle(remoteMessageNotification.title) // Titre de la notification
            .setContentText(remoteMessageNotification.body) // Corps de la notification
            .setAutoCancel(true) // Ferme automatiquement la notification lorsqu'elle est cliquée
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) // Définit le son par défaut pour la notification
            .setContentIntent(
                // Définit un intent qui sera exécuté lorsque l'utilisateur clique sur la notification
                PendingIntent.getActivity(
                    this,
                    0,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE // Spécifie que le `PendingIntent` est immuable
                )
            )

        // Récupère le service de notification du système
        val notificationManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Création d'un canal de notification requis pour les appareils Android Oreo (API 26+) et supérieur
        val channel = NotificationChannel(
            channelId,
            "Channel human readable title",
            NotificationManager.IMPORTANCE_DEFAULT // Importance du canal
        )
        notificationManager.createNotificationChannel(channel) // Crée le channel de notification

        // Affiche la notification à l'utilisateur
        with(NotificationManagerCompat.from(this)) {
            notify(0, notificationBuilder.build()) // Envoie la notification avec l'ID 0
        }
    }
}
