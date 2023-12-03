from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated
from django.utils import timezone
from datetime import timedelta
from .models import User, AppUsage, Transactions

class UserTransactionsView(APIView):
    permission_classes = [IsAuthenticated]

    def get(self, request):
        user = request.user
        thirty_days_ago = timezone.now() - timedelta(days=30)
        
        # Get transactions from the last 30 days
        transactions = Transactions.objects.filter(
            sender=user,
            timestamp__gte=thirty_days_ago
        ).values()

        # Calculate time spent in app
        app_usages = AppUsage.objects.filter(
            user=user,
            session_start__gte=thirty_days_ago
        )
        total_time = sum((usage.session_end - usage.session_start).total_seconds() for usage in app_usages)

        # Format the data for mobile UI
        data = {
            'first_name': user.first_name,
            'transactions': list(transactions),
            'total_time_spent': total_time
        }

        return Response(data)
