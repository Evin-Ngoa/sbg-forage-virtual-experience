from rest_framework import serializers
from .models import User, AppUsage, Transactions

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['id', 'username', 'first_name', 'last_name', 'email', 'address', 'membership', 'date_joined']

class AppUsageSerializer(serializers.ModelSerializer):
    class Meta:
        model = AppUsage
        fields = ['id', 'timestamp', 'user', 'usage_type', 'session_start', 'session_end', 'clicks', 'pages_visited', 'device']

    def to_representation(self, instance):
        """Modify how session duration is represented."""
        representation = super().to_representation(instance)
        # Calculate the session duration in seconds
        session_duration = (instance.session_end - instance.session_start).total_seconds()
        representation['session_duration'] = session_duration
        return representation

class TransactionsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Transactions
        fields = ['id', 'timestamp', 'sender', 'recipient', 'transaction_type', 'amount', 'status']

    def to_representation(self, instance):
        """Modify how transaction amounts are represented."""
        representation = super().to_representation(instance)
        # Ensure the amount is formatted as a string with two decimal places
        representation['amount'] = "{:.2f}".format(instance.amount)
        return representation
